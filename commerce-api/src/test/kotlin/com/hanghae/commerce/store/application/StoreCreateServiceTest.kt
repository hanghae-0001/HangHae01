package com.hanghae.commerce.store.application

import com.hanghae.commerce.store.domain.StoreWriter
import com.hanghae.commerce.store.presentation.dto.CreateStoreRequest
import com.hanghae.commerce.testconfiguration.IntegrationTest
import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserReader
import com.hanghae.commerce.user.domain.UserType
import com.hanghae.commerce.user.domain.UserWriter
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class StoreCreateServiceTest(
    @Autowired
    private val storeCreateService: StoreCreateService,
    @Autowired
    private val storeWriter: StoreWriter,
    @Autowired
    private val userReader: UserReader,
    @Autowired
    private val userWriter: UserWriter,
) {
    @AfterEach
    fun tearDown() {
        userWriter.allDelete()
        storeWriter.allDelete()
    }

    @Test
    fun createStore() {
        // given
        val user = User(
            id = "user1",
            name = "user1",
            age = 20,
            email = "hanghae@naver.com",
            address = "seoul",
            UserType.SELLER,
        )

        userWriter.save(user)

        val foundUser = userReader.findById(user.id)!!

        val request = CreateStoreRequest(
            name = "store1",
            userId = foundUser.id,
        )

        // when
        val createStore = storeCreateService.createStore(request)

        // then
        Assertions.assertThat(createStore.name).isEqualTo("store1")
        Assertions.assertThat(createStore.userId).isEqualTo("user1")
    }

    @Test
    fun createStoreWithDuplicatedStoreName() {
        // given
        val user = User(
            id = "user1",
            name = "user1",
            age = 20,
            email = "hanghae@naver.com",
            address = "seoul",
            UserType.SELLER,
        )

        userWriter.save(user)

        val foundUser = userReader.findById(user.id)!!

        val request1 = CreateStoreRequest(
            name = "store1",
            userId = foundUser.id,
        )

        storeCreateService.createStore(request1)

        val request2 = CreateStoreRequest(
            name = "store1",
            userId = foundUser.id,
        )

        assertThrows<IllegalArgumentException> {
            storeCreateService.createStore(request2)
        }.apply {
            Assertions.assertThat(message).isEqualTo("상점이름이 중복됩니다.")
        }
    }

    @Test
    fun createStoreWithNotSeller() {
        // given
        val user = User(
            id = "user1",
            name = "user1",
            age = 20,
            email = "hanghae@naver.com",
            address = "seoul",
            UserType.CUSTOMER,
        )

        userWriter.save(user)

        val foundUser = userReader.findById(user.id)!!

        val request = CreateStoreRequest(
            name = "store1",
            userId = foundUser.id,
        )

        // when
        assertThrows<IllegalArgumentException> {
            storeCreateService.createStore(request)
        }.apply {
            Assertions.assertThat(message).isEqualTo("해당 유저는 판매자가 아닙니다.")
        }
    }
}
