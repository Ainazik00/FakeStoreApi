package com.example.deveem.presentation.viewmodel.CartViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deveem.domain.model.CartItem
import com.example.deveem.domain.model.Product
import com.example.deveem.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartContentsUseCase: GetCartContentsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {

    private val _cartContents = MutableLiveData<List<CartItem>>()
    val cartContents: LiveData<List<CartItem>> get() = _cartContents

    private val _cartItemCount = MutableLiveData<Int>(0)
    val cartItemCount: LiveData<Int> get() = _cartItemCount

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        getCartContents()
    }

    fun getCartContents() {
        viewModelScope.launch {
            try {
                val cartItems = getCartContentsUseCase.execute()
                _cartContents.value = cartItems
                _cartItemCount.value = cartItems.size
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to load cart contents: ${e.message}"
            }
        }
    }

    fun addToCart(cartItem: Product) {
        viewModelScope.launch {
            try {
                addToCartUseCase.execute(cartItem)
                getCartContents()
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to add to cart: ${e.message}"
            }
        }
    }

    fun removeFromCart(productId: Int) {
        viewModelScope.launch {
            try {
                removeFromCartUseCase.execute(productId)
                getCartContents()
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to remove from cart: ${e.message}"
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            try {
                clearCartUseCase.execute()
                _cartContents.value = emptyList()
                _cartItemCount.value = 0
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to clear cart: ${e.message}"
            }
        }
    }
}
