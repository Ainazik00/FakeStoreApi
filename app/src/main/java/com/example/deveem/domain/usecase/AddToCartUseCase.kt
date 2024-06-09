package com.example.deveem.domain.usecase

import com.example.deveem.domain.model.Product
import com.example.deveem.domain.repository.CartRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend fun execute(product: Product) = cartRepository.addToCart(product)
}
