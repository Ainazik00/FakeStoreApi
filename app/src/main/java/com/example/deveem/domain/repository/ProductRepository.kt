package com.example.deveem.domain.repository


import com.example.deveem.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun insertProducts(products: List<Product>)
}
