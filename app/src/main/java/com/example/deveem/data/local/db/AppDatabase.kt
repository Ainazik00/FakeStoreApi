package com.example.deveem.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.deveem.domain.model.CartItem


@Database(entities = [ProductEntity::class, CartItem::class], version = 1, exportSchema = false)
@TypeConverters(RatingConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
}
