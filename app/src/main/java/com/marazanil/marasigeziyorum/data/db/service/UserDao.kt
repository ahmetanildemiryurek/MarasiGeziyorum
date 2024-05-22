package com.marazanil.marasigeziyorum.data.db.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.marazanil.marasigeziyorum.data.db.entity.User

@Dao
interface UserDao {
    @Insert
     fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
     fun getUserByUsername(username: String): User?
}