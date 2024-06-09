package com.example.deveem.domain.usecase

import com.example.deveem.domain.model.Product
import com.example.deveem.domain.repository.ProductRepository
import javax.inject.Inject

class InsertProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun execute(products: List<Product>) = productRepository.insertProducts(products)
}