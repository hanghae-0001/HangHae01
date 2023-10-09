package com.hanghae.commerce.store.application

import com.hanghae.commerce.store.presentation.dto.CreateStoreRequest
import com.hanghae.commerce.testconfiguration.IntegrationTest
import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserReader
import com.hanghae.commerce.user.domain.UserType
import com.hanghae.commerce.user.domain.UserWriter
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class StoreCreateServiceTest (
    @Autowired
    private val storeCreateService: StoreCreateService,
    @Autowired
    private val userReader: UserReader,
    @Autowired
    private val userWriter: UserWriter,
){
    @Test
    fun createStore() {
        // given
        val user = User(
            id = "user1",
            name = "user1",
            age = 20,
            email = "hanghae@naver.com",
            address = "seoul",
            UserType.SELLER
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
    fun createStoreWith() {
        // given
        val user = User(
            id = "user1",
            name = "user1",
            age = 20,
            email = "hanghae@naver.com",
            address = "seoul",
            UserType.SELLER
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
}
