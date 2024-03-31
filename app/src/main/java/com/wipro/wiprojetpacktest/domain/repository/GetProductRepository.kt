package com.wipro.wiprojetpacktest.domain.repository

import com.wipro.wiprojetpacktest.domain.model.product.ProductItem

interface GetProductRepository {

    suspend fun getProduct() : List<ProductItem>

    suspend fun getCategoryWiseProductList(categoryName : String) : List<ProductItem>

}