package com.example.deveem.domain.usecase

import com.example.deveem.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun execute() = productRepository.getProducts()
}