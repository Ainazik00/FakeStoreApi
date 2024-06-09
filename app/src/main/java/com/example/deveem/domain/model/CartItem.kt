    package com.example.deveem.domain.model

    import androidx.room.Entity
    import androidx.room.PrimaryKey

    @Entity(tableName = "cart")
    data class CartItem(
        @PrimaryKey val id: Int,
        val title: String,
        val price: Double,
        val description: String,
        val category: String,
        val image: String,
        val rating: Rating
    )
