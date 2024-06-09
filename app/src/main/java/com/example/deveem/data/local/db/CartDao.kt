package com.example.deveem.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.deveem.domain.model.CartItem

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    suspend fun getCartContents(): List<CartItem>

    @Insert
    suspend fun addToCart(cartItem: CartItem)

    @Query("DELETE FROM cart WHERE id = :productId")
    suspend fun removeFromCart(productId: Int)

    @Query("DELETE FROM cart")
    suspend fun clearCart()
}
