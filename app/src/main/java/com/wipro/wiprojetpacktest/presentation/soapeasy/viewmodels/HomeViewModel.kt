package com.wipro.wiprojetpacktest.presentation.soapeasy.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wipro.wiprojetpacktest.core.common.Resource
import com.wipro.wiprojetpacktest.domain.use_cases.GetCategoryUseCase
import com.wipro.wiprojetpacktest.domain.use_cases.GetCategoryWiseProductUseCase
import com.wipro.wiprojetpacktest.domain.use_cases.GetProductUseCase
import com.wipro.wiprojetpacktest.presentation.soapeasy.stateholders.CategoryStateHolder
import com.wipro.wiprojetpacktest.presentation.soapeasy.stateholders.ProductStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productUseCase: GetProductUseCase,
    private val categoryUseCase: GetCategoryUseCase,
    private val categoryWiseProductUseCase: GetCategoryWiseProductUseCase
) :
    ViewModel() {

    private val _productListStateHolder = mutableStateOf(ProductStateHolder())
    val productListStateHolder: State<ProductStateHolder> = _productListStateHolder

    private val _categoryListStateHolder = mutableStateOf(CategoryStateHolder())
    val categoryListStateHolder: State<CategoryStateHolder> = _categoryListStateHolder

    init {
        getCategoryList()
        getProductList()
    }

    private fun getCategoryList() = viewModelScope.launch {
        categoryUseCase().onEach {
            when (it) {
                is Resource.Error -> _categoryListStateHolder.value =
                    CategoryStateHolder(error = it.message.toString())

                is Resource.Loading -> _categoryListStateHolder.value =
                    CategoryStateHolder(isLoading = true)

                is Resource.Success -> _categoryListStateHolder.value =
                    CategoryStateHolder(data = it.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun getProductList() = viewModelScope.launch {
        productUseCase().onEach {
            when (it) {
                is Resource.Error -> _productListStateHolder.value =
                    ProductStateHolder(error = it.message.toString())

                is Resource.Loading -> _productListStateHolder.value =
                    ProductStateHolder(isLoading = true)

                is Resource.Success -> _productListStateHolder.value =
                    ProductStateHolder(data = it.data)
            }
        }.launchIn(viewModelScope)
    }

    fun getCategoryWiseProductList(categoryName: String) = viewModelScope.launch {
        categoryWiseProductUseCase(categoryName).onEach {
            when (it) {
                is Resource.Error -> _productListStateHolder.value =
                    ProductStateHolder(error = it.message.toString())

                is Resource.Loading -> _productListStateHolder.value =
                    ProductStateHolder(isLoading = true)

                is Resource.Success -> _productListStateHolder.value =
                    ProductStateHolder(data = it.data)
            }
        }.launchIn(viewModelScope)
    }


}