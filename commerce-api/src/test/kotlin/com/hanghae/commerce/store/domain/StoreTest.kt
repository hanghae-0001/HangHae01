package com.hanghae.commerce.store.domain

import com.hanghae.commerce.testconfiguration.UnitTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@UnitTest
class StoreTest {

    @Test
    fun createStore() {
        val store = Store(id = "1", name = "상점1", "1")

        Assertions.assertThat(store.id).isEqualTo("1")
        Assertions.assertThat(store.name).isEqualTo("상점1")
        Assertions.assertThat(store.userId).isEqualTo("1")
    }
}
