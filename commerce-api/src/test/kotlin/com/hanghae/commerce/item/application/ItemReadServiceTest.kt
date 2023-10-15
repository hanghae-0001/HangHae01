package com.hanghae.commerce.item.application

import com.hanghae.commerce.item.domain.Item
import com.hanghae.commerce.item.domain.ItemWriter
import com.hanghae.commerce.store.domain.Store
import com.hanghae.commerce.store.domain.StoreRepository
import com.hanghae.commerce.store.domain.StoreWriter
import com.hanghae.commerce.testconfiguration.IntegrationTest
import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserRepository
import com.hanghae.commerce.user.domain.UserType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class ItemReadServiceTest(
    @Autowired
    private val itemReadService: ItemReadService,
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val storeRepository: StoreRepository,
    @Autowired
    private val itemWriter: ItemWriter,
    @Autowired
    private val storeWriter: StoreWriter,
) {

    @AfterEach
    fun tearDown() {
        userRepository.allDelete()
        itemWriter.allDelete()
        storeWriter.allDelete()
    }

    @Test
    fun getItemsByStoreId() {
        // given
        val user = User(
            id = "1",
            name = "sangmin",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
            UserType.CUSTOMER,
        )

        val savedUser = userRepository.save(user)

        val store = Store.of(id = "1", name = "store1", savedUser.id)

        val savedStore = storeRepository.save(store)
        val item1 = Item.of("1", "item1", 100, 10, savedStore.id)
        val item2 = Item.of("2", "item2", 200, 20, savedStore.id)
        val item3 = Item.of("3", "item3", 300, 30, savedStore.id)
        itemWriter.save(item1)
        itemWriter.save(item2)
        itemWriter.save(item3)

        // when
        val items = itemReadService.getItemsByStoreId(savedStore.id)

        // then
        Assertions.assertThat(items).hasSize(3)
        Assertions.assertThat(items).extracting("id")
            .containsExactlyInAnyOrder("1", "2", "3")
        Assertions.assertThat(items).extracting("name")
            .containsExactlyInAnyOrder("item1", "item2", "item3")
        Assertions.assertThat(items).extracting("price")
            .containsExactlyInAnyOrder(100, 200, 300)
        Assertions.assertThat(items).extracting("stock")
            .containsExactlyInAnyOrder(10L, 20L, 30L)
        Assertions.assertThat(items).extracting("storeId")
            .containsExactlyInAnyOrder(savedStore.id, savedStore.id, savedStore.id)
    }

    @Test
    fun getItemByItemId() {
        // given
        val user = User(
            id = "1",
            name = "sangmin",
            age = 20,
            email = "hanghae001@gmail.com",
            address = "seoul",
            UserType.CUSTOMER,
        )

        val savedUser = userRepository.save(user)

        val store = Store.of(id = "1", name = "store1", savedUser.id)

        val savedStore = storeRepository.save(store)
        val item = Item.of("1", "item1", 100, 10, savedStore.id)
        itemWriter.save(item)

        // when
        val result = itemReadService.getItemByItemId("1")

        // then
        Assertions.assertThat(result.id).isEqualTo("1")
        Assertions.assertThat(result.name).isEqualTo("item1")
        Assertions.assertThat(result.price).isEqualTo(100)
        Assertions.assertThat(result.stock).isEqualTo(10)
    }
}
