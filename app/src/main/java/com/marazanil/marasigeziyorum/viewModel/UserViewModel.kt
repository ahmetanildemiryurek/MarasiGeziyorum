package com.marazanil.marasigeziyorum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marazanil.marasigeziyorum.data.db.entity.User
import com.marazanil.marasigeziyorum.data.repo.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun getUserByUsername(username: String, callback: (User?) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUserByUsername(username)
            callback(user)
        }
    }

    fun isUsernameTaken(username: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUserByUsername(username)
            callback(user != null)
        }
    }
}
