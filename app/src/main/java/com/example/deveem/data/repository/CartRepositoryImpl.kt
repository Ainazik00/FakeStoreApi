package com.example.deveem.data.repository

import com.example.deveem.data.local.db.CartDao
import com.example.deveem.domain.model.CartItem
import com.example.deveem.domain.model.Product
import com.example.deveem.domain.repository.CartRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {

    override suspend fun getCartContents(): List<CartItem> {
        return cartDao.getCartContents()
    }

    override suspend fun addToCart(product: Product) {
        val cartItem = CartItem(
            id = product.id,
            title = product.title,
            price = product.price,
            description = product.description,
            category = product.category,
            image = product.image,
            rating = product.rating
        )
        cartDao.addToCart(cartItem)
    }

    override suspend fun removeFromCart(productId: Int) {
        cartDao.removeFromCart(productId)
    }

    override suspend fun clearCart() {
        cartDao.clearCart()
    }
}
