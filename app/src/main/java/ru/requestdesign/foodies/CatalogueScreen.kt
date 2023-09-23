package ru.requestdesign.foodies

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CatalogueScreen(
    navController: NavController,
    categories: List<Category>,
    products: List<Product>,
    cartViewModel: CartViewModel,
    catalogueViewModel: CatalogueViewModel
) {
    val cart = cartViewModel.cart

    val totalCost = cart.entries.sumOf { (product, count) -> product.price_current * count/100 }
    val saleCount = cart.entries.sumOf { (product, count) -> (product.price_old?.times(count) ?: product.price_current.times(count)) /100 }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    var selectedCategory by remember { mutableStateOf(categories.first()) }
    var selectedFilters by remember { mutableStateOf<MutableSet<Int>>(mutableSetOf()) }
    var isFilterOpen by remember { mutableStateOf(false) }
    var isSearchActive by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val filteredProducts = filterProductsByCategory(products, selectedCategory, selectedFilters.toSet(), searchText)

    var isBottomSheetOpen by remember { mutableStateOf(false) }
    var darkOverlayAlpha by remember { mutableFloatStateOf(0.0f) }
    val darkOverlayAlphaBg by animateFloatAsState(targetValue = if (isBottomSheetOpen) 0.6f else 0.0f, label = "")
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    LaunchedEffect(isBottomSheetOpen) {
        if (isBottomSheetOpen) {
            bottomSheetState.expand()
            darkOverlayAlpha = 0.6f
        } else {
            bottomSheetState.collapse()
            darkOverlayAlpha = 0.0f
        }
    }

    BottomSheetScaffold(
        sheetContent = {
            AnimatedVisibility(
                visible = isBottomSheetOpen,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                Column (
                    modifier = Modifier
                        .height(312.dp)
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                ){
                    Text(
                        text = "Подобрать блюда",
                        modifier = Modifier
                            .padding(top = 32.dp, start = 24.dp, bottom = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                    val filters = listOf(
                        "Без мяса" to 3,
                        "Острое" to 2,
                        "Со скидкой" to 1
                    )
                    filters.forEach { (filterName, filterId) ->
                        Row(
                            verticalAlignment = CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.clickable {
                                val updatedFilters = selectedFilters.toMutableSet()
                                if (updatedFilters.contains(filterId)) {
                                    updatedFilters.remove(filterId)
                                } else {
                                    updatedFilters.add(filterId)
                                }
                                selectedFilters = updatedFilters
                            }
                        ) {
                            Text(
                                text = filterName,
                                modifier = Modifier.padding(start = 24.dp),
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(
                                checked = selectedFilters.contains(filterId),
                                onCheckedChange = { isChecked ->
                                    val updatedFilters = selectedFilters.toMutableSet()
                                    if (isChecked) {
                                        updatedFilters.add(filterId)
                                    } else {
                                        updatedFilters.remove(filterId)
                                    }
                                    selectedFilters = updatedFilters
                                },
                                modifier = Modifier.padding(end = 16.dp),
                                colors = CheckboxDefaults.colors(checkedColor = Color(0xFFF15412))
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            selectedFilters = selectedFilters.toMutableSet()
                            isBottomSheetOpen = !isBottomSheetOpen
                        },
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFF15412)
                        ),
                        elevation = null
                    ) {
                        Text(
                            text = "Закрыть", fontSize = 16.sp,
                            color = Color.White
                        )
                    }

                }

            } },
        scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = bottomSheetState
        ),
        sheetShape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
        sheetGesturesEnabled = true,
        sheetBackgroundColor = Color(0xFFFFFFFF),
        sheetPeekHeight = 0.dp,
        sheetElevation = 8.dp
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
        {

            if (isSearchActive) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(68.dp)
                        .background(Color(0xFFFFFFFF))
                ) {
                    TextField(value = searchText,
                        onValueChange = { searchText = it },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color(0xFFFFFFFF),
                            placeholderColor = Color(0XFF888D91),
                            leadingIconColor = Color(0xFFF15412),
                            textColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(0XFF888D91),
                        ),
                        leadingIcon = {
                            IconButton(
                                onClick = {
                                    isSearchActive = false
                                    searchText = ""
                                },
                                modifier = Modifier
                                    .align(Center)
                                    .size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Search",
                                    modifier = Modifier.align(Center)
                                )
                            }
                        },
                        placeholder = {
                            Text(text = "Найти блюдо",fontSize = 16.sp)
                        }
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color.White)
                ) {
                    IconButton(
                        onClick = {
                            isBottomSheetOpen = !isBottomSheetOpen
                        },
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter),
                            contentDescription = "Filter",
                            modifier = Modifier
                                .align(Center)
                        )

                    }

                    if (selectedFilters.isNotEmpty()) {
                        Badge(
                            modifier = Modifier.then(
                                Modifier
                                    .padding(start = 32.dp, top = 10.dp)
                                    .size(16.dp)
                                    .background(Color(0xFFF15412), CircleShape)),
                            backgroundColor = Color(0xFFF15412)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(CenterVertically)
                            ) {
                                Text(
                                    text = selectedFilters.size.toString(),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    modifier = Modifier.align(Center)
                                )
                            }
                        } }

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .align(Center)
                    )

                    IconButton(
                        onClick = { isSearchActive = true },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "Search",
                            modifier = Modifier.align(Center)
                        )
                    }
                }
                Slider(
                    items = categories,
                    selectedCategory = selectedCategory,
                    onItemSelected = { category ->
                        selectedCategory = category
                    }
                )
            }

            if (filteredProducts.isNotEmpty() && searchText.isEmpty() && isSearchActive){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Введите название блюда, \n" +
                                "которое ищете",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Center),
                        color = Color.Gray
                    )
                }
            } else if (filteredProducts.isNotEmpty() && searchText.isNotEmpty() && isSearchActive){
                LazyVerticalGrid(
                    if (screenWidth > 500.dp) GridCells.Fixed(4) else GridCells.Fixed(2),
                    modifier = if (totalCost > 0) Modifier
                        .fillMaxSize()
                        .padding(bottom = 72.dp) else Modifier.fillMaxSize()
                ) {
                    items(filteredProducts) { product ->
                        CatalogueItem(
                            product = product,
                            cartViewModel = cartViewModel,
                            navController = navController
                        )
                    }
                }
            } else if (filteredProducts.isNotEmpty()){
                LazyVerticalGrid(
                    if (screenWidth > 500.dp) GridCells.Fixed(4) else GridCells.Fixed(2),
                    modifier = if (totalCost > 0) Modifier
                        .fillMaxSize()
                        .padding(bottom = 72.dp) else Modifier.fillMaxSize()
                ) {
                    items(filteredProducts) { product ->
                        CatalogueItem(
                            product = product,
                            cartViewModel = cartViewModel,
                            navController = navController
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Таких блюд нет :(\nПопробуйте изменить фильтры",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Center),
                        color = Color.Gray
                    )
                }
            }
        }

        if (totalCost > 0) {
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
                            navController.navigate("cart")
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
                        Icon(painter = painterResource(R.drawable.cart), contentDescription = "Cart", modifier = Modifier.align(CenterVertically).size(25.dp).padding(end = 8.dp), tint = Color.White)
                        Text("$totalCost ₽", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                        if(totalCost!=saleCount) Text("$saleCount ₽",modifier = Modifier.padding(start = 8.dp), color = Color(0x99FFFFFF), fontSize = 14.sp, textDecoration = TextDecoration.LineThrough)
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = isBottomSheetOpen,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = darkOverlayAlphaBg))
                    .clickable() {
                        selectedFilters = selectedFilters.toMutableSet()
                        isBottomSheetOpen = !isBottomSheetOpen
                    }
            ){}
        }

    }

}

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
                painter = painterResource(id = R.drawable.img_product),
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
                fontWeight = FontWeight.Bold,
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
                verticalAlignment = CenterVertically,
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
                            modifier = Modifier.align(CenterVertically)
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
                            text = "${product.price_current/100} ₽",
                            modifier = Modifier.align(CenterVertically),
                            color = Color.Black,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        if(product.price_old != null){
                            Text(
                                text = "${product.price_old/100} ₽",
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

@Composable
fun Slider(
    items: List<Category>,
    selectedCategory: Category,
    onItemSelected: (Category) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        items.forEach { category ->
            CategoryCard(
                category = category,
                selected = selectedCategory == category,
                onCategorySelected = { selectedCategory ->
                    onItemSelected(selectedCategory)
                }
            )
        }
    }
}

fun filterProductsByCategory(
    products: List<Product>,
    category: Category,
    selectedFilters: Set<Int>,
    searchText: String,
): List<Product> {
    if (selectedFilters.isEmpty()) {
        if (searchText.isEmpty()) {
            return products.filter { it.category_id == category.id }
        } else {
            return products.filter { product ->
                (product.name.contains(searchText, ignoreCase = true) ||
                        product.description.contains(searchText, ignoreCase = true))
            }
        }
    } else {
        return products.filter { product ->
            product.category_id == category.id && product.tag_ids.any { it in selectedFilters }
        }
    }
}

//val message = "Товар \"${addedProduct.name}\" успешно добавлен в корзину"
//coroutineScope.launch {
//    scaffoldState.snackbarHostState.showSnackbar(message)
//}

