package com.wipro.wiprojetpacktest.presentation.navigation

sealed class NavigationItem(val route: String) {

    object HomeScreen : NavigationItem("home_screen")

    object ProductDetailScreen : NavigationItem("product_details")

}