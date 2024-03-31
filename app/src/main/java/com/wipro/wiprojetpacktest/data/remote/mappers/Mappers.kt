package com.wipro.wiprojetpacktest.data.remote.mappers

import com.wipro.wiprojetpacktest.data.remote.model.product.ProductItemDTO
import com.wipro.wiprojetpacktest.domain.model.product.ProductItem


fun List<ProductItemDTO?>?.toDomain(): List<ProductItem> {
    return this?.map {
        ProductItem(
            id = it?.id ?: 0,
            title = it?.title ?: "",
            description = it?.description ?: "",
            price = it?.price ?: 0.0,
            discountPercentage = it?.discountPercentage ?: 0.0,
            rating = it?.rating ?: 0.0,
            stock = it?.stock ?: 0.0,
            brand = it?.brand ?: "",
            category = it?.category ?: "",
            thumbnail = it?.thumbnail ?: "",
            images = it?.images ?: emptyList()
        )
    } ?: emptyList()
}