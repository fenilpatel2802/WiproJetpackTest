package com.wipro.wiprojetpacktest.home.category

import com.wipro.wiprojetpacktest.MainCoroutineRule
import com.wipro.wiprojetpacktest.data.remote.network.ApiService
import com.wipro.wiprojetpacktest.data.repository.GetCategoryRepositoryImpl
import com.wipro.wiprojetpacktest.domain.repository.GetCategoryRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class GetCategoryRepositoryShould {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var getCategoryRepository: GetCategoryRepository
    private lateinit var apiService: ApiService
    private val categoryList = Mockito.mock<List<String>>()

    @Before
    fun setUp() {
        apiService = Mockito.mock()
        getCategoryRepository = GetCategoryRepositoryImpl(apiService)
    }

    @Test
    fun returnSuccessWhenGetCategoryDataFromBackend() = runTest {
        Mockito.`when`(apiService.getCategoryList()).thenReturn(
            categoryList
        )
        val result = getCategoryRepository.getCategory()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(categoryList, result)
    }


}