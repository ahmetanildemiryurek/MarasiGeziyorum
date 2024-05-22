package com.marazanil.marasigeziyorum.model

import com.marazanil.marasigeziyorum.data.db.entity.Place
import com.marazanil.marasigeziyorum.data.db.service.PlaceDao

class PlaceRepository(private val placeDao: PlaceDao) {

    suspend fun insertPlace(place: Place) {
        placeDao.insertPlace(place)
    }

    fun getAllPlaces(): List<Place> {
        return placeDao.getAllPlaces()
    }
}
