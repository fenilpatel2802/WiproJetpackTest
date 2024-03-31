package com.wipro.wiprojetpacktest.presentation.soapeasy.stateholders

import com.wipro.wiprojetpacktest.domain.model.product.ProductItem

data class CategoryStateHolder(
    val isLoading: Boolean = false,
    val data: List<String>? = null,
    val error: String = ""
)