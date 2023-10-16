package com.hanghae.commerce.store.application

import com.hanghae.commerce.store.domain.Store
import com.hanghae.commerce.store.domain.StoreWriter
import com.hanghae.commerce.testconfiguration.IntegrationTest
import com.hanghae.commerce.user.domain.User
import com.hanghae.commerce.user.domain.UserType
import com.hanghae.commerce.user.domain.UserWriter
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class StoreReadServiceTest(
    @Autowired
    private val storeReadService: StoreReadService,
    @Autowired
    private val storeWriter: StoreWriter,
    @Autowired
    private val userWriter: UserWriter,

) {

    @AfterEach
    fun tearDown() {
        userWriter.allDelete()
        storeWriter.allDelete()
    }

    @Test
    fun getStoresByUserId() {
        val user = userWriter.save(
            User(
                id = "1",
                name = "sangmin",
                age = 20,
                email = "hanghae@gmail.com",
                address = "seoul",
                userType = UserType.SELLER,
            ),
        )

        storeWriter.save(
            Store(
                id = "1",
                name = "상점1",
                "1",
            ),
        )

        storeWriter.save(
            Store(
                id = "2",
                name = "상점2",
                "1",
            ),
        )
        val results = storeReadService.getStoresByUserId(user.id)

        Assertions.assertThat(results).hasSize(2)
        Assertions.assertThat(results).extracting("id")
            .containsExactlyInAnyOrder("1", "2")
        Assertions.assertThat(results).extracting("name")
            .containsExactlyInAnyOrder("상점1", "상점2")
        Assertions.assertThat(results).extracting("userId")
            .containsExactlyInAnyOrder("1", "1")
    }

    @Test
    fun getStore() {
        userWriter.save(
            User(
                id = "1",
                name = "sangmin",
                age = 20,
                email = "hanghae@gmail.com",
                address = "seoul",
                userType = UserType.SELLER,
            ),
        )

        val store = storeWriter.save(
            Store(
                id = "1",
                name = "상점1",
                "1",
            ),
        )

        val result = storeReadService.getStore(store.id)

        Assertions.assertThat(result.id).isEqualTo("1")
        Assertions.assertThat(result.name).isEqualTo("상점1")
        Assertions.assertThat(result.userId).isEqualTo("1")
    }
}
