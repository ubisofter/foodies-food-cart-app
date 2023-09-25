package ru.requestdesign.foodies

import retrofit2.http.GET

interface FoodApi {
    @GET("products.json")
    suspend fun getProducts(): List<Product>

    @GET("categories.json")
    suspend fun getCategories(): List<Category>
}