package com.hanghae.domain.user

import com.hanghae.health.domain.user.User
import com.hanghae.health.domain.user.UserType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
class UserTest {

    @Test
    @DisplayName("구매자를 생성한다.")
    fun createBuyer() {
        // given & when
        val user = User("name", "phone", "address", UserType.BUYER)

        // then
        assertThat(user.name).isEqualTo("name")
        assertThat(user.phone).isEqualTo("phone")
        assertThat(user.address).isEqualTo("address")
        assertThat(user.userType).isEqualTo(UserType.BUYER)
    }

    @Test
    @DisplayName("판매자를 생성한다.")
    fun createSeller() {
        // given & when
        val user = User("name", "phone", "address", UserType.SELLER)

        // then
        assertThat(user.name).isEqualTo("name")
        assertThat(user.phone).isEqualTo("phone")
        assertThat(user.address).isEqualTo("address")
        assertThat(user.userType).isEqualTo(UserType.SELLER)
    }

    @Test
    @DisplayName("이름없이 유저를 생성하면 실패한다")
    fun createUserNotContainName() {
        assertThrows<IllegalArgumentException> {
            User("", "phone", "address", UserType.BUYER)
        }.apply {
            assertThat(message).isEqualTo("이름은 비어 있을 수 없습니다")
        }
    }
}
