package com.wipro.wiprojetpacktest.home

import com.wipro.wiprojetpacktest.MainCoroutineRule
import com.wipro.wiprojetpacktest.core.common.Resource
import com.wipro.wiprojetpacktest.domain.model.product.ProductItem
import com.wipro.wiprojetpacktest.domain.use_cases.GetCategoryUseCase
import com.wipro.wiprojetpacktest.domain.use_cases.GetCategoryWiseProductUseCase
import com.wipro.wiprojetpacktest.domain.use_cases.GetProductUseCase
import com.wipro.wiprojetpacktest.presentation.soapeasy.viewmodels.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelShould {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val productUseCase: GetProductUseCase = Mockito.mock()
    private val categoryUseCase: GetCategoryUseCase = Mockito.mock()
    private val categoryWiseProductUseCase: GetCategoryWiseProductUseCase = Mockito.mock()

    private lateinit var homeViewModel: HomeViewModel

    private val productList: List<ProductItem>? = Mockito.mock()
    private val categoryList: List<String>? = Mockito.mock()

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(productUseCase, categoryUseCase, categoryWiseProductUseCase)
    }

    @Test
    fun validateSuccessStateIsStoredInCategoryWiseProductList() {
        val categoryName = "laptops"
        Mockito.`when`(categoryWiseProductUseCase.invoke(categoryName)).thenReturn(
            flow {
                emit(Resource.Success(productList))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(productList, homeViewModel.productListStateHolder.value.data)
    }

    @Test
    fun validateUserWillSeeProgressBarInitially() = runTest {
        Mockito.`when`(productUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Loading())
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(true, homeViewModel.productListStateHolder.value)
    }

    @Test
    fun validateSuccessStateIsStoredInProductList() = runTest {
        Mockito.`when`(productUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Success(productList))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(productList, homeViewModel.productListStateHolder.value.data)
    }

    @Test
    fun throwErrorInErrorCase() = runTest {
        Mockito.`when`(productUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Error("Something Went Wrong"))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(
            "Something Went Wrong",
            homeViewModel.productListStateHolder.value.error
        )
    }

    @Test
    fun validateSuccessStateIsStoredInCategoryList() = runTest {
        Mockito.`when`(categoryUseCase.invoke()).thenReturn(
            flow {
                emit(Resource.Success(categoryList))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(categoryList, homeViewModel.categoryListStateHolder.value.data)
    }


}