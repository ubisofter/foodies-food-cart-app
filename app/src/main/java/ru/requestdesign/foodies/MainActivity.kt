package ru.requestdesign.foodies

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
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

        WindowCompat.setDecorFitsSystemWindows(window, false)

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

        for (product in products) {
            Log.d("Product", "Price Current: ${product.price_current}")
            Log.d("Product", "Measure Unit: ${product.measure_unit}")
        }

        return products
    }

    @Composable
    private fun App(categories: List<Category>, products: List<Product>) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") {
                SplashScreenFragment(navController)
                window.statusBarColor = Color(0xFFF15412).toArgb()
            }
            composable("catalogue") {
                CatalogueFragment(
                    navController = navController,
                    categories = categories,
                    products = products
                )
                window.statusBarColor = Color(0xFFFFFFFF).toArgb()
            }
        }
    }

}