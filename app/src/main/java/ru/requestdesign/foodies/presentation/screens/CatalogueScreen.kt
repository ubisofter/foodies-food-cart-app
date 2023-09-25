package ru.requestdesign.foodies.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.requestdesign.foodies.R
import ru.requestdesign.foodies.data.Category
import ru.requestdesign.foodies.data.Product
import ru.requestdesign.foodies.presentation.ui.AppTheme
import ru.requestdesign.foodies.presentation.ui.CatalogueItem
import ru.requestdesign.foodies.presentation.ui.Slider
import ru.requestdesign.foodies.util.filterProductsByCategory
import ru.requestdesign.foodies.viewmodel.CartViewModel
import ru.requestdesign.foodies.viewmodel.CatalogueViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CatalogueScreen(
    navController: NavController,
    products: List<Product>,
    categories: List<Category>,
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

    AppTheme {
        BottomSheetScaffold(
            sheetContent = {
                AnimatedVisibility(
                    visible = isBottomSheetOpen,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    Column(
                        modifier = Modifier
                            .height(312.dp)
                            .fillMaxWidth()
                            .align(CenterHorizontally)
                    ) {
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

                }
            },
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
                            modifier = Modifier.align(CenterStart).fillMaxHeight()
                                .padding(top = 16.dp),
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
                                        modifier = Modifier.align(CenterStart)
                                    )
                                }
                            },
                            placeholder = {
                                Text(
                                    text = "Найти блюдо",
                                    fontSize = 16.sp,
                                    modifier = Modifier.align(Center)
                                )
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
                                .align(CenterStart)
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
                                        .background(Color(0xFFF15412), CircleShape)
                                ),
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
                            }
                        }

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

                if (filteredProducts.isNotEmpty() && searchText.isEmpty() && isSearchActive) {
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
                } else if (filteredProducts.isNotEmpty() && searchText.isNotEmpty() && isSearchActive) {
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
                } else if (filteredProducts.isNotEmpty()) {
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
                            Icon(
                                painter = painterResource(R.drawable.cart),
                                contentDescription = "Cart",
                                modifier = Modifier.align(CenterVertically).size(25.dp)
                                    .padding(end = 8.dp),
                                tint = Color.White
                            )
                            Text(
                                "$totalCost ₽",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            if (totalCost != saleCount) Text(
                                "$saleCount ₽",
                                modifier = Modifier.padding(start = 8.dp),
                                color = Color(0x99FFFFFF),
                                fontSize = 14.sp,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                    }
                }
            }

            AnimatedVisibility(
                visible = isBottomSheetOpen,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = darkOverlayAlphaBg))
                        .clickable() {
                            selectedFilters = selectedFilters.toMutableSet()
                            isBottomSheetOpen = !isBottomSheetOpen
                        }
                ) {}
            }

        }
    }

}

