package com.marazanil.marasigeziyorum.data.repo

import com.google.firebase.firestore.FirebaseFirestore
import com.marazanil.marasigeziyorum.data.db.entity.Place

class PlaceRepository {
    private val db = FirebaseFirestore.getInstance()
    private val placeCollection = db.collection("places")

    fun getAllPlaces(callback: (List<Place>) -> Unit) {
        placeCollection.get().addOnSuccessListener { result ->
            val places = result.mapNotNull { it.toObject(Place::class.java) }
            callback(places)
        }
    }

    fun getPlacesByCategory(category: String, callback: (List<Place>) -> Unit) {
        placeCollection.whereEqualTo("category", category).get().addOnSuccessListener { result ->
            val places = result.mapNotNull { it.toObject(Place::class.java) }
            callback(places)
        }
    }
}


