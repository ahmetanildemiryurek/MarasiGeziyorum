/* package com.marazanil.marasigeziyorum.data.db.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.marazanil.marasigeziyorum.data.db.entity.Place

@Dao
interface PlaceDao {
    @Insert
    fun insertPlace(place: Place): Long

    @Query("SELECT * FROM places")
    fun getAllPlaces(): LiveData<List<Place>>
} */
