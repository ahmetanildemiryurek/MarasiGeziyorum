package com.marazanil.marasigeziyorum.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marazanil.marasigeziyorum.data.db.entity.Place
import com.marazanil.marasigeziyorum.model.PlaceRepository
import kotlinx.coroutines.launch

class PlaceViewModel(private val repository: PlaceRepository) : ViewModel() {

    fun insertPlace(place: Place) {
        viewModelScope.launch {
            repository.insertPlace(place)
        }
    }

    fun getAllPlaces(): List<Place> {
        return repository.getAllPlaces()
    }
}
