package com.wipro.wiprojetpacktest.home.category

import com.wipro.wiprojetpacktest.domain.repository.GetCategoryRepository
import com.wipro.wiprojetpacktest.domain.use_cases.GetCategoryUseCase
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class GetCategoryUseCaseShould {

    private lateinit var getCategoryRepository: GetCategoryRepository
    private lateinit var getCategoryUseCase: GetCategoryUseCase
    private val categoryList = Mockito.mock<List<String>>()

    @Before
    fun setUp() {
        getCategoryRepository = Mockito.mock()
        getCategoryUseCase = GetCategoryUseCase(getCategoryRepository)

    }

    @Test
    fun returnCategoryFromUseCaseInSuccess() = runTest {
        `when`(getCategoryRepository.getCategory()).thenReturn(
            categoryList
        )
        getCategoryUseCase.invoke().onEach {
            Assert.assertEquals(categoryList, it.data)
        }
    }

    @Test
    fun returnErrorFromUseCaseInSuccess() = runTest {
        `when`(getCategoryRepository.getCategory()).thenThrow(
            RuntimeException("Something Went Wrong")
        )
        getCategoryUseCase.invoke().onEach {
            Assert.assertEquals("Something Went Wrong", it.message)
        }
    }

}