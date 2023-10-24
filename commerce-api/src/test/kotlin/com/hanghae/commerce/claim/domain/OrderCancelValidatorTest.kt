package com.hanghae.commerce.claim.domain

import com.hanghae.commerce.payment.domain.BankAccount
import com.hanghae.commerce.payment.domain.BankAccountValidator
import com.hanghae.commerce.testconfiguration.UnitTest
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@UnitTest
@DisplayName("Given: validate()")
class OrderCancelValidatorTest {
    @MockK(relaxed = true)
    private lateinit var bankAccountValidator: BankAccountValidator

    @InjectMockKs
    private lateinit var sut: OrderCancelValidator

    @Nested
    @DisplayName("When: 환불시 환불 전용 계좌를 입력했을 때")
    inner class Context1 {
        @Test
        @DisplayName("Then: 계좌의 유효성을 검증한다.")
        fun tc1() {
            // given
            every { bankAccountValidator.validate(any()) } returns true

            // when
            val bankAccount = BankAccount(
                bankName = "testBankName",
                accountNumber = "testAccountNumber",
                accountHolder = "testAccountHolder",
            )
            sut.validate(bankAccount)

            // then
            verify(exactly = 1) { bankAccountValidator.validate(bankAccount) }
        }

        @Test
        @DisplayName("Then: 계좌 유효성 검증이 실패하면, throw IllegalArgumentException")
        fun tc2() {
            // given
            every { bankAccountValidator.validate(any()) } returns false

            // when & then
            val bankAccount = BankAccount(
                bankName = "testBankName",
                accountNumber = "testAccountNumber",
                accountHolder = "testAccountHolder",
            )

            assertThrows<IllegalArgumentException>("Invalid bank account") {
                sut.validate(bankAccount)
            }
        }
    }
}
