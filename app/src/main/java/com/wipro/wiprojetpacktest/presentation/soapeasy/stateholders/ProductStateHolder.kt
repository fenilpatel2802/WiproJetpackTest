package com.wipro.wiprojetpacktest.presentation.soapeasy.stateholders

import com.wipro.wiprojetpacktest.domain.model.product.ProductItem

data class ProductStateHolder(
    val isLoading: Boolean = false,
    val data: List<ProductItem>? = null,
    val error: String = ""
)