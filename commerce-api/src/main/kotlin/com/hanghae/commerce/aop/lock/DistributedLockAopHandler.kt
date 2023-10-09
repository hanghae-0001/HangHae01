package com.hanghae.commerce.aop.lock

import com.hanghae.commerce.aop.common.AopForTransaction
import com.hanghae.commerce.aop.common.CustomSpringELParser
import com.hanghae.commerce.lock.Lock
import com.hanghae.commerce.lock.LockConstants
import com.hanghae.commerce.lock.LockRepository
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Aspect
@Component
class DistributedLockAopHandler(
    private val lockRepository: LockRepository,
    private val aopForTransaction: AopForTransaction,
) {
    val logger = KotlinLogging.logger {}

    @Around("@annotation(com.hanghae.commerce.aop.lock.DistributedLock)")
    @Throws(Throwable::class)
    fun handle(joinPoint: ProceedingJoinPoint): Any? {
        val distributedLock: DistributedLock = resolveDistributedLock(joinPoint)

        val key = parseKeyFromJointPoint(joinPoint)
        val lock: Lock = lockRepository.get(key)

        try {
            lock.lock(distributedLock.leaseTime, distributedLock.timeUnit)
            return aopForTransaction.proceed(joinPoint)
        } catch (e: InterruptedException) {
            throw InterruptedException()
        } finally {
            try {
                lock.unlock()
            } catch (e: IllegalMonitorStateException) {
                logger.info("Lock already release [${resolveSignature(joinPoint).method.name} $key]")
            }
        }
    }

    private fun availableLock(
        lock: Lock,
        distributedLock: DistributedLock,
    ): Boolean {
        return lock.tryLock(
            distributedLock.waitTime,
            distributedLock.leaseTime,
            distributedLock.timeUnit,
        )
    }

    private fun parseKeyFromJointPoint(
        joinPoint: ProceedingJoinPoint,
    ): String {
        val signature = resolveSignature(joinPoint)
        val method: Method = signature.method
        val distributedLock: DistributedLock = method.getAnnotation(DistributedLock::class.java)

        return LockConstants.LOCK_PREFIX + CustomSpringELParser.getDynamicValue(
            signature.parameterNames,
            joinPoint.args,
            distributedLock.key,
        )
    }

    private fun resolveDistributedLock(joinPoint: ProceedingJoinPoint): DistributedLock {
        return (resolveSignature(joinPoint)).method.getAnnotation(DistributedLock::class.java)
    }

    private fun resolveSignature(joinPoint: ProceedingJoinPoint): MethodSignature {
        return joinPoint.signature as MethodSignature
    }
}
