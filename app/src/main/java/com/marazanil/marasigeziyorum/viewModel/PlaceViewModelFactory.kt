package com.marazanil.marasigeziyorum.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlaceViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaceViewModel::class.java)) {
            return PlaceViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
