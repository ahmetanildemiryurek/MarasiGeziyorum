package com.marazanil.marasigeziyorum.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marazanil.marasigeziyorum.data.db.entity.User
import com.marazanil.marasigeziyorum.data.repo.UserRepository

class UserViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun registerUser(firstName: String, lastName: String, username: String, password: String, callback: (Boolean) -> Unit) {
        userRepository.registerUser(firstName, lastName, username, password) { success ->
            if (success) {
                userRepository.getUserDataByUsername(username) { userData ->
                    _user.value = userData
                    callback(success)
                }
            } else {
                callback(success)
            }
        }
    }

    fun loginUser(username: String, password: String, callback: (Boolean) -> Unit) {
        userRepository.loginUser(username, password) { success ->
            if (success) {
                userRepository.getUserDataByUsername(username) { userData ->
                    _user.value = userData
                    callback(success)
                }
            } else {
                callback(success)
            }
        }
    }

    fun logout() {
        _user.value = null
    }

    fun fetchUserData(username: String) {
        userRepository.getUserDataByUsername(username) { userData ->
            _user.value = userData
        }
    }
}
