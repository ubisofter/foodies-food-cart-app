package ru.requestdesign.foodies

import android.os.Bundle
import android.util.Log
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.requestdesign.foodies.data.Category
import ru.requestdesign.foodies.data.FoodApi
import ru.requestdesign.foodies.data.Product
import ru.requestdesign.foodies.screens.CartScreen
import ru.requestdesign.foodies.screens.CatalogueScreen
import ru.requestdesign.foodies.screens.CelebrateScreen
import ru.requestdesign.foodies.screens.ItemScreen
import ru.requestdesign.foodies.screens.SplashScreen
import ru.requestdesign.foodies.viewmodels.CartViewModel
import ru.requestdesign.foodies.viewmodels.CatalogueViewModel
import java.util.concurrent.TimeUnit

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    private val baseUrl = "https://raw.githubusercontent.com/ubisofter/foodies-food-cart-app/main/app/src/main/res/raw/"

    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val foodApi = retrofit.create(FoodApi::class.java)

    private var categories: List<Category> = emptyList()
    private var products: List<Product> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val categoriesJob = async(Dispatchers.IO) {
                    try {
                        categories = foodApi.getCategories()
                        categories = loadCategoriesFromJson(categories.toString())
                    } catch (e: Exception) {
                        Log.d("EXCEPTON GET", e.toString())
                    }
                }

                val productsJob = async(Dispatchers.IO) {
                    try {
                        products = foodApi.getProducts()
                        products = loadProductsFromJson(products.toString())
                    } catch (e: Exception) {
                        Log.d("EXCEPTON GET", e.toString())
                    }
                }
                awaitAll(categoriesJob, productsJob)

                Log.d("CATEGORIES REQUEST","Result: $categories")
                Log.d("PRODUCTS REQUEST","Result: $products")

            } catch (e: Exception) {
                Log.d("REQUEST EXCEPTION","Result: $e")
            }
        }

        setContent {
            App()
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
    private fun App() {
        val navController = rememberNavController()
        val cartViewModel = viewModel<CartViewModel>()
        val catalogueViewModel = viewModel<CatalogueViewModel>()

        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") {
                SplashScreen(navController, categories, products)
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