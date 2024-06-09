package com.example.deveem.data.local.db

import androidx.room.TypeConverter
import com.example.deveem.domain.model.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RatingConverter {

    @TypeConverter
    fun fromRating(rating: Rating): String {
        val type = object : TypeToken<Rating>() {}.type
        return Gson().toJson(rating, type)
    }

    @TypeConverter
    fun toRating(ratingString: String): Rating {
        val type = object : TypeToken<Rating>() {}.type
        return Gson().fromJson(ratingString, type)
    }
}
