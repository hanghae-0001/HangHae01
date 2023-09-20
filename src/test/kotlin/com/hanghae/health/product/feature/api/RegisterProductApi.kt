package com.hanghae.health.product.feature.api

import com.hanghae.health.common.Scenario
import com.hanghae.health.product.domain.Category
import com.hanghae.health.product.domain.TemperatureZone
import com.hanghae.health.product.feature.RegisterProduct
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.springframework.http.HttpStatus

 class RegisterProductApi {

    private val name = "name"
    private val code = "code"
    private val description = "description"
    private val brand = "brand"
    private val maker = "maker"
    private val origin = "origin"
    private val category = Category.ELECTRONICS
    private val temperatureZone = TemperatureZone.ROOM_TEMPERATURE
    private val weightInGrams = 1000L
    private val widthInMillimeters = 100L
    private val heightInMillimeters = 100L
    private val lengthInMillimeters = 100L

    fun request(): Scenario {
        val request = RegisterProduct.Request(
            name,
            code,
            description,
            brand,
            maker,
            origin,
            category,
            temperatureZone,
            weightInGrams,  // gram
            widthInMillimeters,  // 너비 mm
            heightInMillimeters,  // 높이 mm
            lengthInMillimeters, // 길이 mm
        )

        //when
        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/products")
            .then().log().all()
            .statusCode(HttpStatus.CREATED.value())
        return Scenario()
    }
}
