package com.hanghae.commerce.data.domain.payment

import com.hanghae.commerce.data.common.PrimaryKeyEntity
import com.hanghae.commerce.data.domain.order.OrderEntity
import com.hanghae.commerce.data.domain.payment.vo.BankAccountVO
import com.hanghae.commerce.payment.domain.PaymentStatus
import jakarta.persistence.*

@Entity
@Table(name = "payment")
class PaymentEntity(
    @Transient private val identifier: String,

    @JoinColumn(name = "order_id")
    @OneToOne(fetch = FetchType.LAZY)
    val order: OrderEntity,

    @Column(nullable = false)
    val paymentAmount: Int,

    @Embedded
    @Column(nullable = false)
    var bankAccountVO: BankAccountVO,

    @Column(nullable = false)
    val status: PaymentStatus = PaymentStatus.READY,
) : PrimaryKeyEntity(identifier)
