package com.hanghae.commerce.store.domain

import com.hanghae.commerce.testconfiguration.IntegrationTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class StoreRepositoryTest(
    @Autowired
    private val storeRepository: StoreRepository,
) {
    @Test
    fun save() {
        // given
        val store = Store.of(id = "1", name = "store1", "1")

        // when
        val savedStore = storeRepository.save(store)

        // then
        Assertions.assertThat(savedStore.id).isEqualTo("1")
        Assertions.assertThat(savedStore.name).isEqualTo("store1")
        Assertions.assertThat(savedStore.userId).isEqualTo("1")
    }
}
