package com.wipro.wiprojetpacktest.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.wipro.wiprojetpacktest.presentation.soapeasy.components.CategoryItemComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CategoryItemShould {

    @get:Rule
    val composeRule = createComposeRule()
    private var categoryName: String = "laptops"

    @Before
    fun setUp() {
        composeRule.setContent {
            CategoryItemComponent(categoryName, {})
        }
    }

    @Test
    fun containCorrectTitle() {
        composeRule.onNodeWithText("laptops").assertIsDisplayed()
    }

}