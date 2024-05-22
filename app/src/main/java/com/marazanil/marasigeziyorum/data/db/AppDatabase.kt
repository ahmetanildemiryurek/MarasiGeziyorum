package com.marazanil.marasigeziyorum.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.marazanil.marasigeziyorum.data.db.entity.Place
import com.marazanil.marasigeziyorum.data.db.entity.User
import com.marazanil.marasigeziyorum.data.db.service.PlaceDao
import com.marazanil.marasigeziyorum.data.db.service.UserDao

@Database(entities = [User::class, Place::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun placeDao(): PlaceDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database").build()
    }
}
