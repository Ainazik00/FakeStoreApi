package com.example.deveem.domain.repository

import com.example.deveem.domain.model.CartItem
import com.example.deveem.domain.model.Product

interface CartRepository {
    suspend fun getCartContents(): List<CartItem>
    suspend fun addToCart(cartItem: Product)
    suspend fun removeFromCart(productId: Int)
    suspend fun clearCart()
}
