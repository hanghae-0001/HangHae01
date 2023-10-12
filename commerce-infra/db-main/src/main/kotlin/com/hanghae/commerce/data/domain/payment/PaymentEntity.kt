package com.hanghae.commerce.data.domain.payment

import com.hanghae.commerce.data.common.PrimaryKeyEntity
import com.hanghae.commerce.data.domain.order.OrderEntity
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.persistence.Table


@Entity
@Table(name = "payments")
class PaymentEntity(
    @Transient
    private val id: String,

    @OneToOne
    var order: OrderEntity,

    var cardNumber: String,

) : PrimaryKeyEntity(id) {

//    companion object {
//        fun from(payment: Payment): PaymentEntity {
//            return PaymentEntity(
//                id = UUID.randomUUID().toString(),
//                order = payment.order.toEntity(),
//                cardNumber = payment.cardNumber,
//            )
//        }
//    }

}
