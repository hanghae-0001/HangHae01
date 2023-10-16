package com.hanghae.commerce.cart.presentaion

import com.hanghae.commerce.cart.application.FavoriteItemReaderService
import com.hanghae.commerce.cart.application.FavoriteItemWriterService
import com.hanghae.commerce.cart.presentaion.dto.AddFavoriteItemRequest
import com.hanghae.commerce.cart.presentaion.dto.GetFavoriteItemResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/favorite-items")
class FavoriteItemController(
    private val favoriteItemReaderService: FavoriteItemReaderService,
    private val favoriteItemWriterService: FavoriteItemWriterService,
) {

    /**
     * 유저의 즐겨찾기 목록을 조회한다.
     */
    @GetMapping("/users/{userId}")
    fun getCartByUserId(@PathVariable userId: Long): List<GetFavoriteItemResponse> {
        return favoriteItemReaderService.getFavoriteItemsByUserId(userId).let {
            GetFavoriteItemResponse.of(it)
        }
    }

    @PostMapping
    fun addCartItem(
        @RequestBody @Valid
        addFavoriteItemRequest: AddFavoriteItemRequest,
    ): GetFavoriteItemResponse? {
        return favoriteItemWriterService.addFavoriteItem(addFavoriteItemRequest)?.let {
            GetFavoriteItemResponse.of(it)
        }
    }
}
