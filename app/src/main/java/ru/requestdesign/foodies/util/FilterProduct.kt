package ru.requestdesign.foodies.util

import ru.requestdesign.foodies.data.Category
import ru.requestdesign.foodies.data.Product

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