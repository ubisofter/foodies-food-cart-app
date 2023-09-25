package ru.requestdesign.foodies.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.requestdesign.foodies.data.Category

@Composable
fun CategoryCard(category: Category, selected: Boolean, onCategorySelected: (Category) -> Unit) {
    Card(
        backgroundColor = if (selected) Color(0xFFF15412) else Color.White,
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            .clickable {
                onCategorySelected(category)
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = category.name,
            modifier = Modifier.padding(12.dp),
            fontSize = 16.sp,
            color = if (selected) Color.White else Color.Black,
        )
    }
}