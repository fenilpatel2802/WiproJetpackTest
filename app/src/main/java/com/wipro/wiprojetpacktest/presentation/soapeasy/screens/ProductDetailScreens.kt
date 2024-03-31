package com.wipro.wiprojetpacktest.presentation.soapeasy.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDetailScreen(
    navController: NavController,
    id: String?,
    image: String?,
    productName: String?,
    price: String?,
    stock: String?,
    categoryName: String?,
    brandName: String?,
    description: String?,
) {
    Scaffold(topBar =
    {
        TopAppBar(
            title = { Text(text = "Product Detail") },
            navigationIcon =
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            })
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

        ) {
            item {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = productName ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(
                        start = 10.dp,
                        end = 10.dp
                    ),
                    fontSize = 18.sp,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 5.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "$$price",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Stock available : $stock",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(
                    thickness = 0.3.dp,
                    modifier = Modifier.padding(
                        start = 10.dp,
                        end = 10.dp,
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                        ),
                    text = "Category : $categoryName",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 10.dp
                        ),
                    text = "Brand : $brandName",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(
                    thickness = 0.3.dp,
                    modifier = Modifier.padding(
                        start = 10.dp,
                        end = 10.dp,
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                        ),
                    text = "Detail",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFFFFA500)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                        ),
                    text = description ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
            }
        }
    }
}
