package com.wipro.wiprojetpacktest.data.repository

import com.wipro.wiprojetpacktest.data.remote.mappers.toDomain
import com.wipro.wiprojetpacktest.data.remote.network.ApiService
import com.wipro.wiprojetpacktest.domain.model.product.ProductItem
import com.wipro.wiprojetpacktest.domain.repository.GetProductRepository
import javax.inject.Inject

class GetProductRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    GetProductRepository {

    override suspend fun getProduct(): List<ProductItem> {
        return apiService.getProductList().products!!.toDomain()
    }

    override suspend fun getCategoryWiseProductList(categoryName: String): List<ProductItem> {
        return apiService.getCategoryWiseProductList(categoryName).products!!.toDomain()
    }

}