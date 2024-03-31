package com.wipro.wiprojetpacktest.home.product

import com.wipro.wiprojetpacktest.domain.model.product.ProductItem
import com.wipro.wiprojetpacktest.domain.repository.GetProductRepository
import com.wipro.wiprojetpacktest.domain.use_cases.GetProductUseCase
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class GetProductUseCaseShould {

    private lateinit var getProductRepository: GetProductRepository
    private lateinit var getProductUseCase: GetProductUseCase
    private val productList = Mockito.mock<List<ProductItem>>()

    @Before
    fun setUp() {
        getProductRepository = Mockito.mock()
        getProductUseCase = GetProductUseCase(getProductRepository)

    }

    @Test
    fun returnProductFromUseCaseInSuccess() = runTest {
        `when`(getProductRepository.getProduct()).thenReturn(
            productList
        )
        getProductUseCase.invoke().onEach {
            Assert.assertEquals(productList, it.data)
        }
    }

    @Test
    fun returnErrorFromUseCaseInSuccess() = runTest {
        `when`(getProductRepository.getProduct()).thenThrow(
            RuntimeException("Something Went Wrong")
        )
        getProductUseCase.invoke().onEach {
            Assert.assertEquals("Something Went Wrong", it.message)
        }
    }

}