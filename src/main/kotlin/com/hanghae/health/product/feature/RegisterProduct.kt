package com.hanghae.health.product.feature

import com.hanghae.health.product.domain.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
class RegisterProduct(
    private val productRepository: ProductJpaRepository? = null,
) {

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    fun request(@RequestBody request: Request) {
        validateProductCodeExists(request.code)
        val product = request.toDomain()
        productRepository!!.save(product)
    }

    private fun validateProductCodeExists(code: String) {
        productRepository!!.findAll().stream()
            .filter { product: Product -> product.code.equals(code) }
            .findFirst()
            .ifPresent { product: Product? ->
                throw IllegalArgumentException(
                    "이미 등록된 상품코드입니다. %s".formatted(code),
                )
            }
    }

    @JvmRecord
    data class Request(
        @NotBlank(message = "상품명은 필수입니다.")
        val name: String,
        @NotBlank(message = "상품코드는 필수입니다.")
        val code: String,
        @NotBlank(message = "상품설명은 필수입니다.")
        val description: String,
        @NotBlank(message = "브랜드는 필수입니다.")
        val brand: String,
        @NotBlank(message = "제조사는 필수입니다.")
        val maker: String,
        @NotBlank(message = "원산지는 필수입니다.")
        val origin: String,
        @NotNull(message = "카테고리는 필수입니다.")
        val category: Category,
        @NotNull(message = "온도대는 필수입니다.")
        val temperatureZone: TemperatureZone,
        @NotNull(message = "무게는 필수입니다.")
        @Min(value = 0, message = "무게는 0보다 작을 수 없습니다.")
        val weightInGrams: Long,
        @NotNull(message = "상품의 너비는 필수입니다.")
        @Min(value = 0, message = "상품의 너비는 0보다 작을 수 없습니다.")
        val widthInMillimeters: Long,
        @NotNull(message = "상품의 높이는 필수입니다.")
        @Min(value = 0, message = "상품의 높이는 0보다 작을 수 없습니다.")
        val heightInMillimeters: Long,
        @NotNull(message = "상품의 길이는 필수입니다.")
        @Min(value = 0, message = "상품의 길이는 0보다 작을 수 없습니다.")
        val lengthInMillimeters: Long,
    ) {
        fun toDomain(): Product {
            return Product(
                name = name,
                code = code,
                description = description,
                brand = brand,
                maker = maker,
                origin = origin,
                category = category,
                temperatureZone = temperatureZone,
                weightInGrams = weightInGrams,
                ProductSize(
                    widthInMillimeters = widthInMillimeters,
                    heightInMillimeters = heightInMillimeters,
                    lengthInMillimeters = lengthInMillimeters,
                ),
            )
        }
    }
}
