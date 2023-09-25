package ru.requestdesign.foodies.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.requestdesign.foodies.R
import ru.requestdesign.foodies.data.Product
import ru.requestdesign.foodies.presentation.ui.AppTheme
import ru.requestdesign.foodies.viewmodel.CartViewModel

@Composable
fun CartItem(
    product: Product,
    itemCount: Int,
    cartViewModel: CartViewModel,
    navController: NavController
) {
    val isDiscounted = product.price_old != null
    val totalPrice = product.price_current * itemCount / 100

    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth().clickable {
                    navController.navigate("item/${product.id}")
                }
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
                            verticalAlignment = Alignment.CenterVertically,
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
                                        modifier = Modifier.align(Alignment.CenterVertically)
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
                                        modifier = Modifier.align(Alignment.CenterVertically)
                                    )
                                }

                                Column(
                                    modifier = Modifier
                                        .padding(start = 16.dp)
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Bottom,
                                    horizontalAlignment = Alignment.End,
                                ) {
                                    Text(
                                        text = (product.price_current / 100).toString() + "₽",
                                        fontSize = 16.sp,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.padding(end = 16.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                    if (product.price_old != null) {
                                        Text(
                                            text = (product.price_old / 100).toString() + "₽",
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
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                        color = Color.Black,
                                        style = MaterialTheme.typography.h6,
                                        fontSize = 16.sp
                                    )
                                    if (isDiscounted) {
                                        Text(
                                            text = "${product.price_old?.div(100)} ₽",
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
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
}