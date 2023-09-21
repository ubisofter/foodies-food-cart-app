package ru.requestdesign.foodies

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    private val _cart = mutableStateMapOf<Product, Int>()
    val cart: Map<Product, Int> get() = _cart

    fun addToCart(product: Product, count: Int) {
        _cart[product] = _cart.getOrDefault(product, 0) + count
    }

    // Добавьте функцию для получения количества продуктов в корзине по продукту
    fun getItemCount(product: Product): Int {
        return _cart[product] ?: 0
    }
}