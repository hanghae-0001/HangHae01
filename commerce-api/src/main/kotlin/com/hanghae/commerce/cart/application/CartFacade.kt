package com.hanghae.commerce.cart.application

import com.hanghae.commerce.cart.presentaion.dto.AddCartItemRequest
import com.hanghae.commerce.cart.presentaion.dto.GetCartItemsResponse
import com.hanghae.commerce.user.application.UserReaderService
import org.springframework.stereotype.Service

@Service
class CartFacade(
    private val cartWriterService: CartWriterService,
    private val cartReaderService: CartReaderService,
    private val userReaderService: UserReaderService,
) {

    fun getCartItems(userId: String): List<GetCartItemsResponse> {
        return cartReaderService.getCartItemsByUserId(userId = userId).let {
            GetCartItemsResponse.of(it)
        }
    }

    fun addCartItem(request: AddCartItemRequest): GetCartItemsResponse {
        val cartId = cartReaderService.getCartByUserId(request.userId) ?: cartWriterService.addCart(userId = request.userId)
        return cartWriterService.addCartItem(cartId, request.itemId).let {
            GetCartItemsResponse.of(it)
        }
    }
}
