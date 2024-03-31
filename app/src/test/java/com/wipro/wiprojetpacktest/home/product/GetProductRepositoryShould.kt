package com.wipro.wiprojetpacktest.home.product

import com.wipro.wiprojetpacktest.MainCoroutineRule
import com.wipro.wiprojetpacktest.data.remote.mappers.toDomain
import com.wipro.wiprojetpacktest.data.remote.model.product.ProductListResponseDTO
import com.wipro.wiprojetpacktest.data.remote.network.ApiService
import com.wipro.wiprojetpacktest.data.repository.GetProductRepositoryImpl
import com.wipro.wiprojetpacktest.domain.repository.GetProductRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class GetProductRepositoryShould {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var getProductRepository: GetProductRepository
    private lateinit var apiService: ApiService
    private val productList = Mockito.mock<ProductListResponseDTO>()

    @Before
    fun setUp() {
        apiService = Mockito.mock()
        getProductRepository = GetProductRepositoryImpl(apiService)
    }

    @Test
    fun returnSuccessWhenGetProductDataFromBackend() = runTest {
        Mockito.`when`(apiService.getProductList()).thenReturn(
            productList
        )
        val result = getProductRepository.getProduct()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(productList.products!!.toDomain(), result)
    }

    @Test
    fun returnSuccessWhenGetCategoryWiseProductDataFromBackend() = runTest {
        val categoryName = "laptops"
        Mockito.`when`(apiService.getCategoryWiseProductList(categoryName)).thenReturn(
            productList
        )
        val result = getProductRepository.getCategoryWiseProductList(categoryName)
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(productList.products!!.toDomain(), result)
    }



}