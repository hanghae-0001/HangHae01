package com.hanghae.commerce.order.domain

enum class OrderStatus {
    PAYMENT_WAIT,
    PAYMENT_COMPLETE,
    DELIVERY_PREPARE,
    DELIVERY_COMPLETE,
    DELIVERY_CANCEL,
    ORDER_CANCEL,
    ORDER_COMPLETE,
    ORDER_FAIL,
    ;
}
