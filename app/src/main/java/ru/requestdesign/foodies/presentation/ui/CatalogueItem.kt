package ru.requestdesign.foodies.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import ru.requestdesign.foodies.R
import ru.requestdesign.foodies.data.Product
import ru.requestdesign.foodies.viewmodel.CartViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CatalogueItem(navController: NavController, product: Product, cartViewModel: CartViewModel) {
    val itemCount = cartViewModel.getItemCount(product)
    val tagIcons = mutableListOf<Painter>()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    if (1 in product.tag_ids) { tagIcons.add(painterResource(id = R.drawable.tag_sale)) }
    if (2 in product.tag_ids) { tagIcons.add(painterResource(id = R.drawable.tag_spicy)) }
    if (3 in product.tag_ids) { tagIcons.add(painterResource(id = R.drawable.tag_meatless)) }
    if (4 in product.tag_ids) { tagIcons.add(painterResource(id = R.drawable.tag_4)) }

    AppTheme {
        Card(
            modifier = if (screenWidth > 500.dp) Modifier
                .fillMaxWidth()
                .aspectRatio(7f / 11)
                .padding(8.dp) else Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 5)
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color(0xFFF5F5F5),
            onClick = {
                // TODO: Реализовать запоминание выбранных фильтров при перемещениями между экранами
                navController.navigate("item/${product.id}")
            }
        ) {
            Column(
            ) {
                Image(
                    painter = if (product.image.equals("1.jpg")) rememberAsyncImagePainter(R.drawable.img_product) else rememberAsyncImagePainter(
                        product.image
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillHeight
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.name,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(16.dp, 0.dp),
                    maxLines = 2,
                    minLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.padding(16.dp, 0.dp),
                    text = "${product.measure} ${product.measure_unit}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    if (itemCount > 0) {
                        IconButton(
                            modifier = Modifier
                                .background(Color(0xFFFFFFFF), RoundedCornerShape(8.dp))
                                .size(42.dp),
                            onClick = {
                                if (itemCount > 0) {
                                    cartViewModel.addToCart(product, -1)
                                }
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
                                .background(Color(0xFFFFFFFF), RoundedCornerShape(8.dp))
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
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            if (product.price_old != null) {
                                Text(
                                    text = "${product.price_old / 100} ₽",
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                tagIcons.forEach { icon ->
                    Image(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}