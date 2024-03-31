package com.wipro.wiprojetpacktest.home.product

import com.wipro.wiprojetpacktest.domain.model.product.ProductItem
import com.wipro.wiprojetpacktest.domain.repository.GetProductRepository
import com.wipro.wiprojetpacktest.domain.use_cases.GetCategoryWiseProductUseCase
import com.wipro.wiprojetpacktest.domain.use_cases.GetProductUseCase
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class GetCategoryWiseProductUseCaseShould {

    private lateinit var getProductRepository: GetProductRepository
    private lateinit var getCategoryWiseProductUseCase: GetCategoryWiseProductUseCase
    private val productList = Mockito.mock<List<ProductItem>>()

    @Before
    fun setUp() {
        getProductRepository = Mockito.mock()
        getCategoryWiseProductUseCase = GetCategoryWiseProductUseCase(getProductRepository)

    }

    @Test
    fun returnProductFromUseCaseInSuccess() = runTest {
        val categoryName = "laptops"
        `when`(getProductRepository.getCategoryWiseProductList(categoryName)).thenReturn(
            productList
        )
        getCategoryWiseProductUseCase.invoke(categoryName).onEach {
            Assert.assertEquals(productList, it.data)
        }
    }

    @Test
    fun returnErrorFromUseCaseInSuccess() = runTest {
        val categoryName = "laptops"
        `when`(getProductRepository.getCategoryWiseProductList(categoryName)).thenThrow(
            RuntimeException("Something Went Wrong")
        )
        getCategoryWiseProductUseCase.invoke(categoryName).onEach {
            Assert.assertEquals("Something Went Wrong", it.message)
        }
    }

}