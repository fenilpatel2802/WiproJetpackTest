package com.wipro.wiprojetpacktest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wipro.wiprojetpacktest.presentation.soapeasy.screens.HomeScreen
import com.wipro.wiprojetpacktest.presentation.soapeasy.screens.ProductDetailScreen

@Composable
fun Navigation(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = NavigationItem.HomeScreen.route) {

        composable(NavigationItem.HomeScreen.route) {
            HomeScreen(navHostController)
        }

        composable(NavigationItem.ProductDetailScreen.route + "/{id}/{image}/{productName}/{price}/{stock}/{categoryName}/{brandName}/{description}") {
            val id = it.arguments?.getString("id")
            val image = it.arguments?.getString("image")
            val productName = it.arguments?.getString("productName")
            val price = it.arguments?.getString("price")
            val stock = it.arguments?.getString("stock")
            val categoryName = it.arguments?.getString("categoryName")
            val brandName = it.arguments?.getString("brandName")
            val description = it.arguments?.getString("description")
            ProductDetailScreen(
                navController = navHostController,
                id,
                image,
                productName,
                price,
                stock,
                categoryName,
                brandName,
                description
            )
        }
    }
}