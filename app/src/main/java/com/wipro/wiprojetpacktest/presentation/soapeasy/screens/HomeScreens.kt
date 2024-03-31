package com.wipro.wiprojetpacktest.presentation.soapeasy.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wipro.wiprojetpacktest.presentation.navigation.NavigationItem
import com.wipro.wiprojetpacktest.presentation.soapeasy.components.CategoryItemComponent
import com.wipro.wiprojetpacktest.presentation.soapeasy.components.ProductItemComponent
import com.wipro.wiprojetpacktest.presentation.soapeasy.viewmodels.HomeViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Home") }) }) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            val result = viewModel.productListStateHolder.value
            val categoryResult = viewModel.categoryListStateHolder.value

            if (result.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.testTag("progress"))
                }
            }

            if (result.error.isNotBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = result.error)
                }
            }

            Column {

                if (categoryResult.data != null) {
                    LazyRow(
                        modifier = Modifier
                            .padding(10.dp)
                            .testTag("category_list")
                    ) {
                        items(categoryResult.data) { item ->
                            CategoryItemComponent(item) {
                                viewModel.getCategoryWiseProductList(it)
                            }
                        }
                    }
                    Log.e("TAG", "---->> HomeScreen: ${categoryResult.data}")
                }

                if (result.data != null) {
                    LazyVerticalGrid(
                        GridCells.Fixed(2),
                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                end = 10.dp,
                            )
                            .testTag("product_list")
                    ) {
                        items(result.data) { item ->
                            ProductItemComponent(item) {
                                val encodedUrl = URLEncoder.encode(
                                    item.thumbnail,
                                    StandardCharsets.UTF_8.toString()
                                )
                                val argsString =
                                    "/${item.id}/${encodedUrl}/${item.title}/${item.price}/${item.stock}/${item.category}/${item.brand}/${item.description}"
                                navHostController.navigate(NavigationItem.ProductDetailScreen.route + argsString)
                            }
                        }
                    }


                }
            }

        }

    }

}