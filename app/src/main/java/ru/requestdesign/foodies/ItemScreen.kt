package ru.requestdesign.foodies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun ItemScreen(
    navController: NavController,
    product: Product,
    cartViewModel: CartViewModel
) {
    Scaffold(
        content = {it
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painter = if(product.image.equals("1.jpg")) rememberAsyncImagePainter(R.drawable.img_product) else rememberAsyncImagePainter(product.image),
                        contentDescription = product.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(9f/8)
                            .clip(shape = RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillHeight
                    )

                    Text(text = product.name, fontSize = 34.sp,modifier = Modifier.padding(start = 16.dp, top = 16.dp))

                    Text(
                        text = product.description,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        color = Color(0x99000000)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    val variables = listOf(
                        "Вес" to "${product.measure}${product.measure_unit}",
                        "Энерг.ценность" to "${product.energy_per_100_grams} ккал",
                        "Белки" to "${product.proteins_per_100_grams} г",
                        "Жиры" to "${product.fats_per_100_grams} г",
                        "Углеводы" to "${product.carbohydrates_per_100_grams} г"
                    )

                    for ((name, value) in variables) {
                        Spacer(modifier = Modifier.height(1.dp).background(Color(0x1F000000)).padding(16.dp).fillMaxWidth())
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = name,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Start,
                                color = Color(0x99000000)
                            )
                            Text(
                                text = value,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.End
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(1.dp).background(Color(0x1F000000)).padding(16.dp).fillMaxWidth())
                }
            }
            FloatingActionButton(
                modifier = Modifier.padding(start = 16.dp, top = 28.dp).size(44.dp),
                backgroundColor = Color(0xFFFFFFFF),
                onClick = {
                    navController.navigateUp()
                },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Назад",
                    tint = Color(0xFF333333)
                )

            }

            if (cartViewModel.getItemCount(product) > 0) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Bottom
                ){
                    Box(
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                            .weight(2f)
                    ) {
                        IconButton(
                            modifier = Modifier
                                .width(56.dp)
                                .height(56.dp)
                                .padding(start = 16.dp, top = 16.dp)
                                .background(Color(0xFFF15412), RoundedCornerShape(8.dp))
                                .align(Alignment.TopStart),
                            onClick = {
                                if (cartViewModel.getItemCount(product) > 0) {
                                    cartViewModel.addToCart(product, -1)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Remove",
                                tint = Color(0xFFFFFFFF),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        Text(
                            text = cartViewModel.getItemCount(product).toString(),
                            fontSize = 22.sp,
                            modifier = Modifier.padding(top = 22.dp).align(Alignment.TopCenter),
                            style = MaterialTheme.typography.h6
                        )

                        IconButton(
                            modifier = Modifier
                                .width(56.dp)
                                .height(56.dp)
                                .padding(end = 16.dp,top = 16.dp)
                                .background(Color(0xFFF15412), RoundedCornerShape(8.dp))
                                .align(Alignment.TopEnd),
                            onClick = {
                                cartViewModel.addToCart(product, +1)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color(0xFFFFFFFF),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                            .weight(3f)
                    ) {
                        Button(
                            onClick = {
                                navController.navigateUp()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .padding(end = 12.dp, top = 16.dp)
                                .background(Color(0xFFF15412), RoundedCornerShape(8.dp)),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF15412)),
                            elevation = null,
                        ) {
                            Text(
                                text = "Сохранить",
                                color = Color.White
                            )
                        }
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Button(
                            onClick = {
                                cartViewModel.addToCart(product, 1)
                                navController.navigateUp()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                                .background(Color(0xFFF15412), RoundedCornerShape(8.dp)),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF15412)),
                            elevation = null,
                        ) {
                            Text(
                                text = "В корзину за ${product.price_current} ₽",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    )
}