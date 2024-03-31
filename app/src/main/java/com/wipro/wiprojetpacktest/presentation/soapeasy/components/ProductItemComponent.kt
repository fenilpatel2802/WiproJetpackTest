package com.wipro.wiprojetpacktest.presentation.soapeasy.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wipro.wiprojetpacktest.domain.model.product.ProductItem


@Composable
fun ProductItemComponent(productItem: ProductItem, onClick: (ProductItem) -> Unit) {
    Column(
        modifier = Modifier
            .padding(
                start = 5.dp,
                top = 10.dp,
                end = 5.dp,
                bottom = 10.dp,
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClick.invoke(productItem)
            }
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .testTag("productItem")
        ) {
            AsyncImage(
                model = productItem.thumbnail,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .testTag("productImage"),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = productItem.title,
            modifier = Modifier
                .padding(
                    top = 7.dp
                )
                .testTag("productTitle"),
            fontSize = 15.sp,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "$${productItem.price}",
            modifier = Modifier.testTag("productPrice"),
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium
        )
    }
}