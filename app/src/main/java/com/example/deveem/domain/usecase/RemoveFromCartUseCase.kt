package com.example.deveem.domain.usecase

import com.example.deveem.domain.repository.CartRepository
import javax.inject.Inject

class RemoveFromCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend fun execute(productId: Int) = cartRepository.removeFromCart(productId)
}
