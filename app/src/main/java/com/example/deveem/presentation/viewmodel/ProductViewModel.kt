package com.example.deveem.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deveem.domain.model.Product
import com.example.deveem.domain.repository.CartRepository
import com.example.deveem.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val cartRepository: CartRepository,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _cartItemCount = MutableLiveData<Int>()
    val cartItemCount: LiveData<Int> get() = _cartItemCount

    init {
        viewModelScope.launch {
            _cartItemCount.value = cartRepository.getCartContents().size
        }
    }

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun loadProducts() {
        viewModelScope.launch {
            try {
                val products = getProductsUseCase.execute()
                _products.value = products
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to load products: ${e.message}"
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            cartRepository.addToCart(product)
            _cartItemCount.value = cartRepository.getCartContents().size
        }
    }

    fun removeFromCart(productId: Int) {
        viewModelScope.launch {
            cartRepository.removeFromCart(productId)
            _cartItemCount.value = cartRepository.getCartContents().size
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartRepository.clearCart()
            _cartItemCount.value = 0
        }
    }
}
