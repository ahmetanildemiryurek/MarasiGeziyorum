package com.marazanil.marasigeziyorum.data.db.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.marazanil.marasigeziyorum.data.db.entity.Place

@Dao
interface PlaceDao {
    @Insert
     fun insertPlace(place: Place)

    @Query("SELECT * FROM places")
     fun getAllPlaces(): List<Place>
}

