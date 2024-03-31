package com.wipro.wiprojetpacktest.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.wipro.wiprojetpacktest.presentation.MainActivity
import org.junit.Rule
import org.junit.Test

class HomeListFeatures {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun validateProgressBarVisible() {
        composeRule.apply {
            onNodeWithTag("progress").assertIsDisplayed()
        }
    }

    @Test
    fun validateTopAppBarTitleIsVisibleCorrectly() {
        composeRule.apply {
            onNodeWithText("Home").assertIsDisplayed()
        }
    }

    @Test
    fun validateIsCategoryListVisible() {
        composeRule.apply {
            Thread.sleep(2000)
            onNodeWithTag("category_list").assertIsDisplayed()
        }
    }

    @Test
    fun validateIsProductListVisible() {
        composeRule.apply {
            Thread.sleep(2000)
            onNodeWithTag("product_list").assertIsDisplayed()
        }
    }

    @Test
    fun validateNavigationFromMovieListToMovieDetails() {
        composeRule.apply {
            Thread.sleep(2000)
            onNodeWithTag("product_list").onChildAt(0).performClick()
            onNodeWithText("Product Detail").assertIsDisplayed()
        }
    }

}