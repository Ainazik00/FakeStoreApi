package com.example.deveem.data.repository

import com.example.deveem.data.local.db.ProductDao
import com.example.deveem.data.local.db.ProductEntity
import com.example.deveem.data.remote.api.ApiService
import com.example.deveem.domain.model.Product
import com.example.deveem.domain.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productApiService: ApiService,
    private val productDao: ProductDao
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        val products = productApiService.getProducts()
        productDao.insertAll(products.map { product ->
            ProductEntity(
                id = product.id,
                title = product.title,
                description = product.description,
                price = product.price,
                imageUrl = product.image,
                rating = product.rating
            )
        })
        return products
    }

    override suspend fun insertProducts(products: List<Product>) {
        productDao.insertAll(products.map { product ->
            ProductEntity(
                id = product.id,
                title = product.title,
                description = product.description,
                price = product.price,
                imageUrl = product.image,
                rating = product.rating
            )
        })
    }
}
