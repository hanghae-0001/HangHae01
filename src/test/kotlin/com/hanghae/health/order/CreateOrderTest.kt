package com.hanghae.health.order

import org.junit.jupiter.api.Test

class CreateOrderTest {

    @Test
    fun `주문에 성공한다`() {
    }

    @Test
    fun `주문에 실패한다 - 장바구니가 비어있는 경우 EmptyCartException이 발생한다`() {
    }

    @Test
    fun `주문에 실패한다 - 상품 재고가 10개일 때 11개 주문하면 OutOfStockException이 발생한다`() {
    }

    @Test
    fun `주문에 실패한다 - 상품 재고가 1000개일 때 251명이 동시에 4개씩 주문하면 1개의 주문은 실패하고 재고는 0이된다`() {
    }

    @Test
    fun `주문에 실패한다 - 배송 정보가 없고 미리 등록된 주소가 없는 경우 NoAddressToDeleveryException이 발생한다`() {
    }

}
