package ru.requestdesign.foodies

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    private var categories: List<Category> = emptyList()
    private var products: List<Product> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        val categoriesInputStream = resources.openRawResource(R.raw.categories)
        val categoriesJson = categoriesInputStream.bufferedReader().use { it.readText() }

        val productsInputStream = resources.openRawResource(R.raw.products)
        val productsJson = productsInputStream.bufferedReader().use { it.readText() }

        categories = loadCategoriesFromJson(categoriesJson)
        products = loadProductsFromJson(productsJson)

        setContent {
            App(categories, products)
        }
    }

    private fun loadJsonFromAssets(fileName: String): String {
        return applicationContext.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    }

    private fun loadCategoriesFromJson(jsonString: String): List<Category> {
        val listType = object : TypeToken<List<Category>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    private fun loadProductsFromJson(jsonString: String): List<Product> {
        val listType = object : TypeToken<List<Product>>() {}.type
        val products = Gson().fromJson<List<Product>>(jsonString, listType)

        for (product in products) { }

        return products
    }

    @Composable
    private fun App(categories: List<Category>, products: List<Product>) {
        var navController = rememberNavController()
        var cartViewModel = viewModel<CartViewModel>()
        var catalogueViewModel = viewModel<CatalogueViewModel>()

        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") {
                SplashScreen(navController)
                window.statusBarColor = Color(0xFFF15412).toArgb()
                window.navigationBarColor = Color(0xFFF15412).toArgb()
            }
            composable("catalogue") {
                CatalogueScreen(
                    navController = navController,
                    categories = categories,
                    products = products,
                    cartViewModel = cartViewModel,
                    catalogueViewModel = catalogueViewModel
                )
                window.statusBarColor = Color(0xFFFFFFFF).toArgb()
                window.navigationBarColor = Color(0x1E000000).toArgb()
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            composable("item/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")
                val product = products.firstOrNull { it.id.toString() == productId }
                if (product != null) {
                    ItemScreen(product = product, cartViewModel = cartViewModel, navController = navController)
                }
            }
            composable("cart") {
                CartScreen(
                    navController = navController,
                    cartViewModel = cartViewModel
                )
            }
            composable("celebrate") {
                CelebrateScreen(navController, applicationContext)
            }
        }
    }

}