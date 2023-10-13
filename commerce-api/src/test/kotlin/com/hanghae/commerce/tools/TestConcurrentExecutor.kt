package com.hanghae.commerce.tools

import org.springframework.stereotype.Component
import java.util.concurrent.Callable
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.Future

@Component
class TestConcurrentExecutor {

    @Throws(InterruptedException::class)
    fun <T> executableFutures(
        threadCount: Int,
        callable: Callable<T>,
    ): List<Future<T>> {
        val executorService = Executors.newFixedThreadPool(32)
        val futures: MutableList<Future<T>> = mutableListOf()

        for (i in 0 until threadCount) {
            futures.add(executorService.submit(callable) as Future<T>)
        }

        return futures
    }

    @Throws(InterruptedException::class)
    fun executeOrderInMultiThread(
        threadCount: Int,
        runnable: Runnable,
    ) {
        val executorService = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(threadCount)

        for (i in 0 until threadCount) {
            try {
                executorService.submit(runnable)
            } finally {
                latch.countDown()
            }
        }
        latch.await()
    }
}
