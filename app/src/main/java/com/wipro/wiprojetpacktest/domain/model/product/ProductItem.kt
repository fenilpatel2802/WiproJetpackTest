package com.wipro.wiprojetpacktest.domain.model.product


data class ProductItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Double,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)
