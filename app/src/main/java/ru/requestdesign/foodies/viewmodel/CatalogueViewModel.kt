package ru.requestdesign.foodies.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CatalogueViewModel : ViewModel() {
    private var _selectedFilters by mutableStateOf<Set<Int>>(emptySet())
    val selectedFilters: Set<Int>
        get() = _selectedFilters

    fun updateFilters(newFilters: Set<Int>) {
        _selectedFilters = newFilters
    }
}