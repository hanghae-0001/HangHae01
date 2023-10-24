package com.hanghae.commerce.order.application

import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.item.domain.ItemRepository
import com.hanghae.commerce.order.domain.OrderRepository
import com.hanghae.commerce.order.domain.OrderStatus
import com.hanghae.commerce.order.domain.command.OrderCommand
import com.hanghae.commerce.order.exception.SoldOutException
import com.hanghae.commerce.testconfiguration.EnableTestcontainers
import com.hanghae.commerce.testconfiguration.IntegrationTest
import com.hanghae.commerce.tools.TestConcurrentExecutor
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.ExecutionException

@IntegrationTest
@EnableTestcontainers
@DisplayName("Given: orderCreate()")
class OrderFacadeTest {

    @Autowired
    private lateinit var testConcurrentExecutor: TestConcurrentExecutor

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var itemRepository: ItemRepository

    @Autowired
    private lateinit var sut: OrderFacade

    @Nested
    @DisplayName("When: 25,000원짜리 상품 재고가 5개 일 때,")
    internal inner class Context1 {

        private lateinit var item: Item

        @BeforeEach
        fun setUp() {
            item = Item.of(
                id = "1",
                name = "Item Fixture",
                price = 25000,
                stock = 5L,
                storeId = "1",
            )
            itemRepository.save(item)
        }

        @AfterEach
        fun tearDown() {
            itemRepository.deleteAll()
        }

        @Test
        @DisplayName("Then: 6개 주문하면, SoldOutException 발생한다.")
        fun tc1() {
            // when
            assertThatThrownBy {
                sut.order(
                    createOrderCommand(item.id, 6),
                )

                // then
            }.isInstanceOf(SoldOutException::class.java).hasMessage("재고가 부족합니다.")
        }

        @Test
        @DisplayName("Then: 존재하지 않는 상품번호를 요청하면, IllegalArgumentException 발생한다.")
        fun tc2() {
            // when
            assertThatThrownBy {
                sut.order(
                    createOrderCommand("NOT_EXIST_ITEM_ID", 7),
                )

                // then
            }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("존재하지 않는 상품입니다.")
        }
    }

    @Nested
    @DisplayName("When: 상품 재고가 1000개 일 때,")
    internal inner class Context2 {

        private lateinit var item: Item

        @BeforeEach
        fun setUp() {
            item = Item.of(
                id = "1",
                name = "Item Fixture",
                price = 25000,
                stock = 1000L,
                storeId = "1",
            )
            itemRepository.save(item)
        }

        @AfterEach
        fun tearDown() {
            itemRepository.deleteAll()
        }

        @Test
        @DisplayName("Then: 요청 300개가 동시에 3개씩 주문하면, 남는 재고는 100개이다.")
        fun tc1() {
            testConcurrentExecutor.executeOrderInMultiThread(
                300,
            ) {
                sut.order(
                    createOrderCommand(item.id, 3),
                )
            }
            Thread.sleep(2000)

            // then
            val remainStock: Long = itemRepository.findById(item.id)!!.stock
            assertThat(remainStock).isEqualTo(100L)
        }

        @Test
        @DisplayName("Then: 요청 200개가 동시에 5개씩 주문하면, 남는 재고는 0개이다.")
        fun tc2() {
            // when
            testConcurrentExecutor.executeOrderInMultiThread(
                threadCount = 200,
            ) {
                sut.order(
                    createOrderCommand(item.id, 5),
                )
            }
            Thread.sleep(2000)

            // then
            val remainStock: Long = itemRepository.findById(item.id)!!.stock
            assertThat(remainStock).isEqualTo(0L)
        }

        @Test
        @DisplayName("Then: 요청 300개가 동시에 4개씩 주문하면, 요청 50개는 SoldOutException 발생한다.")
        fun tc3() {
            // when
            val futures = testConcurrentExecutor.executableFutures<String>(
                threadCount = 300,
            ) {
                sut.order(
                    createOrderCommand(item.id, 4),
                )
            }

            val results: MutableList<String> = mutableListOf()
            val soldOutExceptions: MutableList<SoldOutException?> = mutableListOf()
            for (future in futures) {
                try {
                    results.add(future.get())
                } catch (e: InterruptedException) {
                    soldOutExceptions.add(e.cause as SoldOutException?)
                } catch (e: ExecutionException) {
                    soldOutExceptions.add(e.cause as SoldOutException?)
                }
            }
            Thread.sleep(2000)

            // then
            assertThat(results).hasSize(250)
            assertThat(soldOutExceptions).hasSize(50)
            assertThat(soldOutExceptions.map { it!!.message }.toList()).containsOnly("재고가 부족합니다.")
        }
    }

    @Nested
    @DisplayName("When: 주문 요청 시 배송정보가 전달되지 않는다면,")
    internal inner class Context3 {
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
    @DisplayName("When: 주문이 정상적으로 완료되면,")
    internal inner class Context4 {

        private lateinit var item: Item

        @BeforeEach
        fun setUp() {
            item = Item.of(
                id = "1",
                name = "Item Fixture",
                price = 25000,
                stock = 1000L,
                storeId = "1",
            )
            itemRepository.save(item)
        }

        @AfterEach
        fun tearDown() {
            itemRepository.deleteAll()
        }

        @Test
        @DisplayName("Then: 주문이 생성된다.")
        fun tc1() {
            // when
            val orderId = sut.order(
                createOrderCommand(item.id, 1),
            )

            // then
            val savedOrder = orderRepository.findById(orderId)
            assertThat(savedOrder).isNotNull
            assertThat(savedOrder!!.orderAmount).isEqualTo(item.price * 1)
            assertThat(savedOrder.deliveryFee).isEqualTo(2500)
            assertThat(savedOrder.paymentAmount).isEqualTo(savedOrder.orderAmount + savedOrder.deliveryFee)
            assertThat(savedOrder.orderItemList).isNotEmpty
        }

        @Test
        @DisplayName("Then: 생성된 주문은 '결제 대기' 상태이다.")
        fun tc2() {
            // when
            val orderId = sut.order(
                createOrderCommand(item.id, 1),
            )

            // then
            val savedOrder = orderRepository.findById(orderId)
            assertThat(savedOrder).isNotNull
            assertThat(savedOrder!!.status).isEqualTo(OrderStatus.PAYMENT_WAIT)
        }
    }

    private fun createOrderCommand(itemId: String, quantity: Int) = OrderCommand(
        userId = "testUserId",
        orderItemList = listOf(
            OrderCommand.OrderItem(
                itemId = itemId,
                quantity = quantity,
            ),
        ),
    )
}
