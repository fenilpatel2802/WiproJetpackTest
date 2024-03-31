package com.wipro.wiprojetpacktest.data.remote.network

import com.wipro.wiprojetpacktest.data.remote.model.product.ProductListResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // product list
    @GET("products")
    suspend fun getProductList(): ProductListResponseDTO

    // category list
    @GET("products/categories")
    suspend fun getCategoryList(): List<String>

    // category wise product list
    @GET("products/category/{category}")
    suspend fun getCategoryWiseProductList(@Path("category") categoryName: String?): ProductListResponseDTO

}