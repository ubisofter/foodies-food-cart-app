package ru.requestdesign.foodies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CartScreen(navController: NavController, cartViewModel: CartViewModel) {
    val cart = cartViewModel.cart

    val totalCost = cart.entries.sumOf { (product, count) -> product.price_current * count / 100 }
    val saleCount = cart.entries.sumOf { (product, count) -> (product.price_old?.times(count) ?: product.price_current.times(count)) / 100 }

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
    ) {it
        if (cart.isNotEmpty()) {
            val cartItems = cart.entries.toList()

            LazyVerticalGrid(
                GridCells.Adaptive(minSize = 340.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 72.dp)
            ) {
                itemsIndexed(cartItems) { _, (product, itemCount) ->
                    CartItem(
                        product = product,
                        itemCount = itemCount,
                        cartViewModel = cartViewModel
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
                    .padding(16.dp)
            ) {
                Text(
                    text = "Пусто, выберите блюда\nв каталоге :)",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun CartItem(product: Product, itemCount: Int, cartViewModel: CartViewModel) {
    val isDiscounted = product.price_old != null
    val totalPrice = product.price_current * itemCount / 100

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_product),
                    contentDescription = null,
                    modifier = Modifier
                        .size(96.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .weight(1f)
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                )
                Column(
                    modifier = Modifier.weight(3f).padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = product.name,
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        if (itemCount > 0) {
                            IconButton(
                                modifier = Modifier
                                    .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                                    .size(42.dp),
                                onClick = {
                                    cartViewModel.addToCart(product, -1)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = "Remove",
                                    tint = Color(0xFFF15412),
                                    modifier = Modifier.align(CenterVertically)
                                )
                            }

                            Text(
                                text = itemCount.toString(),
                                fontSize = 16.sp,
                                modifier = Modifier.padding(horizontal = 16.dp),
                                style = MaterialTheme.typography.h6
                            )

                            IconButton(
                                modifier = Modifier
                                    .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                                    .size(42.dp),
                                onClick = {
                                    cartViewModel.addToCart(product, +1)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add",
                                    tint = Color(0xFFF15412),
                                    modifier = Modifier.align(CenterVertically)
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = End,
                            ){
                                Text(
                                    text = (product.price_current/100).toString()+"₽",
                                    fontSize = 16.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(end = 16.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                if (product.price_old!=null){
                                    Text(
                                        text = (product.price_old/100).toString()+"₽",
                                        fontSize = 14.sp,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.padding(end = 16.dp),
                                        textDecoration = TextDecoration.LineThrough
                                    )
                                }
                            }
                        } else {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    cartViewModel.addToCart(product, +1)
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.Black,
                                    backgroundColor = Color.White
                                ),
                                elevation = null,
                            ) {
                                Text(
                                    text = "${product.price_current / 100} ₽",
                                    modifier = Modifier.align(CenterVertically),
                                    color = Color.Black,
                                    style = MaterialTheme.typography.h6,
                                    fontSize = 16.sp
                                )
                                if (isDiscounted) {
                                    Text(
                                        text = "${product.price_old?.div(100)} ₽",
                                        modifier = Modifier
                                            .align(CenterVertically)
                                            .padding(start = 8.dp),
                                        color = Color(0x99000000),
                                        style = MaterialTheme.typography.h6,
                                        fontSize = 14.sp,
                                        textDecoration = TextDecoration.LineThrough
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}