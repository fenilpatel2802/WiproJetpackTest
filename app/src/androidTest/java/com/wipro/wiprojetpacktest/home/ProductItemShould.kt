package com.wipro.wiprojetpacktest.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.wipro.wiprojetpacktest.domain.model.product.ProductItem
import com.wipro.wiprojetpacktest.presentation.soapeasy.components.ProductItemComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductItemShould {

    @get:Rule
    val composeRule = createComposeRule()
    private lateinit var productItem: ProductItem

    @Before
    fun setUp() {
        productItem = ProductItem(
            id = 1,
            title = "iPhone 9",
            description = "An apple mobile which is nothing like apple",
            price = 549.0,
            discountPercentage = 12.96,
            rating = 4.69,
            stock = 94.0,
            brand = "Apple",
            category = "smartphones",
            thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            images = emptyList()
        )
        composeRule.setContent {
            ProductItemComponent(productItem = productItem, {})
        }
    }


    @Test
    fun containCorrectTitle(){
        composeRule.onNodeWithText("iPhone 9").assertIsDisplayed()
    }
    @Test
    fun containCorrectProductPrice(){
        composeRule.onNodeWithText("$549.0").assertIsDisplayed()
    }


}