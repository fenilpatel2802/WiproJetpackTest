package com.wipro.wiprojetpacktest.presentation.soapeasy.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryItemComponent(categoryName: String, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .border(
                1.dp,
                Color(0xFF6E6F65),
                shape = RoundedCornerShape(7.dp)
            ) // 0xFF is the alpha value followed by RGB color
            .padding(12.dp)
            .clickable {
                onClick.invoke(categoryName)
            }
    ) {
        Text(
            modifier = Modifier.testTag("categoryTitle"),
            text = categoryName,
            fontSize = 15.sp,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}