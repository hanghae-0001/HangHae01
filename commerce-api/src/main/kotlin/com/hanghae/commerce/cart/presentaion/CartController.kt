package com.hanghae.commerce.cart.presentaion

import com.hanghae.commerce.cart.application.CartReaderService
import com.hanghae.commerce.cart.application.CartWriterService
import com.hanghae.commerce.cart.presentaion.dto.AddCartItemRequest
import com.hanghae.commerce.cart.presentaion.dto.GetCartItemsResponse
import com.hanghae.commerce.cart.presentaion.dto.UpdateCartItemRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/carts")
class CartController(
    private val cartReaderService: CartReaderService,
    private val cartWriterService: CartWriterService,
) {

    /**
     * 유저의 장바구니 목록을 조회한다.
     */
    @GetMapping("/users/{userId}")
    fun getCartByUserId(@PathVariable userId: Long): List<GetCartItemsResponse> {
        return cartReaderService.getCartItemsByUserId(userId).let {
            GetCartItemsResponse.of(it)
        }
    }

    @PostMapping("/add-item")
    fun addCartItem(
        @RequestBody @Valid
        addCartItemRequest: AddCartItemRequest,
    ): GetCartItemsResponse? {
        return cartWriterService.addCartItem(addCartItemRequest)?.let {
            GetCartItemsResponse.of(it)
        }
    }

    @PatchMapping("/cart-item")
    fun updateCartItemQuantity(
        @RequestBody @Valid
        updateCartItemRequest: UpdateCartItemRequest,
    ): GetCartItemsResponse? {
        return cartWriterService.updateCartItemQuantity(updateCartItemRequest)?.let {
            GetCartItemsResponse.of(it)
        }
    }
}
