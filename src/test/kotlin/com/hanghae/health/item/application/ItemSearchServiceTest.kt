package com.hanghae.health.item.application

import com.hanghae.health.testconfiguration.UnitTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@UnitTest
@DisplayName("search()")
class ItemSearchServiceTest {

    @Nested
    @DisplayName("When: 조건을 전달하지 않을 시, ")
    internal inner class when_no_condition_is_provided {

        // TODO: 기본 정렬 조건에 대한 정의 필요
        @Test
        @DisplayName("Then: 즐겨찾기가 많이 등록된 순서대로 조회한다.")
        fun tc1() {
        }
    }

    @Nested
    @DisplayName("When: 조건을 전달할 시, ")
    internal inner class when_condition_is_provided {

        @Test
        @DisplayName("Then: 검색어가 전달되면, 검색어를 포함한 상품목록을 조회할 수 있다.")
        fun tc1() {
        }

        @Test
        @DisplayName("Then: 카테고리 목록을 전달하면, 해당 카테고리 목록에 속한 상품목록을 조회할 수 있다.")
        fun tc2() {
        }

        @Test
        @DisplayName("Then: 상점 ID를 전달하면, 해당 상점에 속한 상품목록을 조회할 수 있다.")
        fun tc3() {
        }
    }
}
