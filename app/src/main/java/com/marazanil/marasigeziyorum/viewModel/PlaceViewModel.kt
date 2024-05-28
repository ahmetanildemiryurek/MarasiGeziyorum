package com.marazanil.marasigeziyorum.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marazanil.marasigeziyorum.data.db.entity.Place
import com.marazanil.marasigeziyorum.data.repo.PlaceRepository

class PlaceViewModel : ViewModel() {

    private val repository = PlaceRepository()
    private val _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> get() = _places

    fun fetchAllPlaces() {
        repository.getAllPlaces { places ->
            _places.value = places
        }
    }

    fun fetchPlacesByCategory(category: String) {
        repository.getPlacesByCategory(category) { places ->
            _places.value = places
        }
    }

    fun fetchNaturalPlaces() {
        repository.getPlacesByCategory("natural") { places ->
            _places.value = places
        }
    }

    fun fetchWaterPlaces() {
        repository.getPlacesByCategory("water") { places ->
            _places.value = places
        }
    }

    fun fetchParks() {
        repository.getPlacesByCategory("parks") { places ->
            _places.value = places
        }
    }

    fun fetchCastles() {
        repository.getPlacesByCategory("castles") { places ->
            _places.value = places
        }
    }
    fun fetchHolyPlaces() {
        repository.getPlacesByCategory("holy") { places ->
            _places.value = places
        }
    }
    fun fetchHistoricalPlaces() {
        repository.getPlacesByCategory("historical") { places ->
            _places.value = places
        }
    }
    fun fetchMuseum() {
        repository.getPlacesByCategory("museum") { places ->
            _places.value = places
        }
    }
}
