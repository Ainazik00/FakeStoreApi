package com.example.deveem.data.remote.api


import com.example.deveem.domain.model.Product
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun fetchProducts(): List<Product> = apiService.getProducts()
}