package ru.requestdesign.foodies.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.requestdesign.foodies.ui.AppTheme
import ru.requestdesign.foodies.ui.CartItem
import ru.requestdesign.foodies.viewmodels.CartViewModel

@Composable
fun CartScreen(navController: NavController, cartViewModel: CartViewModel) {
    val cart = cartViewModel.cart

    val totalCost = cart.entries.sumOf { (product, count) -> product.price_current * count / 100 }
    val saleCount = cart.entries.sumOf { (product, count) ->
        (product.price_old?.times(count) ?: product.price_current.times(count)) / 100
    }

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Корзина", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                tint = Color(0xFFF15412),
                                contentDescription = "Back"
                            )
                        }
                    },
                    backgroundColor = Color.White
                )
            }
        ) {
            it
            if (totalCost != 0) {
                val cartItems = cart.entries.toList()
                val visibleCartItems = cartItems.filter { (_, itemCount) -> itemCount >= 1 }

                LazyVerticalGrid(
                    GridCells.Adaptive(minSize = 340.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 72.dp)
                ) {
                    itemsIndexed(visibleCartItems) { _, (product, itemCount) ->
                        CartItem(
                            product = product,
                            itemCount = itemCount,
                            cartViewModel = cartViewModel,
                            navController = navController
                        )
                        Spacer(modifier = Modifier.height(1.dp).background(Color(0x1F000000)))
                    }
                }

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .height(72.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {

                        Button(
                            onClick = {
                                navController.navigate("celebrate")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .padding(start = 16.dp, end = 16.dp)
                                .background(Color(0xFFF15412), RoundedCornerShape(8.dp)),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF15412)),
                            elevation = null,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Заказать за $totalCost ₽",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )

                            if (totalCost != saleCount) {
                                Text(
                                    text = "$saleCount ₽",
                                    modifier = Modifier.padding(start = 8.dp),
                                    color = Color(0x99FFFFFF),
                                    fontSize = 14.sp,
                                    textDecoration = TextDecoration.LineThrough
                                )
                            }
                        }
                    }
                }

            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Center),
                        text = "Пусто, выберите блюда\nв каталоге :)",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}