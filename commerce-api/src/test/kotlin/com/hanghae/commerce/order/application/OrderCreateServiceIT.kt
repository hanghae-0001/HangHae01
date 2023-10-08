package com.hanghae.commerce.order.application

import com.hanghae.commerce.item.application.ItemWriter
import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.order.domain.OrderCreateService
import com.hanghae.commerce.order.domain.OrderItem
import com.hanghae.commerce.order.domain.command.OrderCreateCommand
import com.hanghae.commerce.order.exception.SoldOutException
import com.hanghae.commerce.testconfiguration.EnableTestcontainers
import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
@EnableTestcontainers
@DisplayName("Given: orderCreate()")
class OrderCreateServiceIT(
    @Autowired var itemWriter: ItemWriter,
//    @Autowired
//    var itemStockCountService: ItemStockCountService,
    @Autowired var sut: OrderCreateService,
) {

    @Nested
    @DisplayName("When: 25,000원짜리 상품 재고가 5개 일 때,")
    internal inner class when_stock_size_is_5 {

        lateinit var item: Item

        @BeforeEach
        fun setUp() {
            item = Item.of(
                id = "1",
                name = "Item Fixture",
                price = 25000,
                stock = 5L,
            )
            itemWriter.write(item)
        }

        @AfterEach
        fun tearDown() {
            itemWriter.deleteAll()
        }

        @Test
        @DisplayName("Then: 6개 주문하면, OutOfStockException 발생한다.")
        fun tc1() {
            // when
            assertThatThrownBy {
                sut.create(
                    orderCreateCommandFixture(
                        itemId = item.id,
                        price = 20_000,
                        quantityPerRequest = 6,
                    ),
                )

                // then
            }.isInstanceOf(SoldOutException::class.java)
                .hasMessage("주문한 상품량이 재고량보다 큽니다.")
        }

        @Test
        @DisplayName("Then: 존재하지 않는 상품번호를 요청하면, IllegalArgumentException 발생한다.")
        fun tc2() {
            // when
            assertThatThrownBy {
                sut.create(
                    orderCreateCommandFixture(
                        itemId = "NOT_EXIST_ITEM_ID",
                        price = 20_000,
                        quantityPerRequest = 7,
                    ),
                )

                // then
            }.isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("존재하지 않는 상품입니다.")
        }
    }

    @Nested
    @DisplayName("When: 상품 재고가 1000개 일 때,")
    internal inner class when_stock_size_is_1000 {

        @Test
        @DisplayName("Then: 요청 300개가 동시에 3개씩 주문하면, 남는 재고는 100개이다.")
        fun tc1() {
        }

        @Test
        @DisplayName("Then: 요청 200개가 동시에 5개씩 주문하면, 남는 재고는 0개이다.")
        fun tc2() {
        }

        @Test
        @DisplayName("Then: 요청 300개가 동시에 4개씩 주문하면, 요청 50개는 OutOfStockException 발생한다.")
        fun tc3() {
        }
    }

    @Nested
    @DisplayName("When: 주문 요청 시 배송정보가 전달되지 않는다면,")
    internal inner class when_requesting_an_order_delivery_information_is_not_provided {
        @Test
        @DisplayName("Then: 미리 등록된 주소로 배송이 진행된다.")
        fun tc1() {
        }

        @Test
        @DisplayName("Then: 미리 등록된 주소가 없다면 NoAddressToDeliveryException 발생한다.")
        fun tc2() {
        }
    }

    @Nested
    @DisplayName("When: 주문요청 시 장바구니에 등록된 상품이 없다면,")
    internal inner class when_cart_item_is_empty {
        @Test
        @DisplayName("Then: EmptyCartException 발생한다.")
        fun tc1() {
        }
    }

    @Nested
    @DisplayName("When: 주문이 정상적으로 완료되면,")
    internal inner class when_create_success {
        @Test
        @DisplayName("Then: 주문이 생성된다.")
        fun tc1() {
        }

        @Test
        @DisplayName("Then: 생성된 주문은 '결제 대기' 상태이다.")
        fun tc2() {
        }
    }

    private fun orderCreateCommandFixture(
        itemId: String,
        price: Int,
        quantityPerRequest: Int,
    ): OrderCreateCommand {
        return OrderCreateCommand(
            listOf(
                OrderItem(
                    id = "velit",
                    itemId = itemId,
                    price = price,
                    name = "Marta Stokes",
                    quantity = quantityPerRequest,
                ),
            ),
        )
    }

    private fun orderCreateCommandFixture(vararg orderItems: OrderItem): OrderCreateCommand {
        return OrderCreateCommand(orderItems.toList())
    }
}
