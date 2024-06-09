package com.example.deveem.data.remote.api

import retrofit2.http.GET
import com.example.deveem.domain.model.Product

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}
