package com.marazanil.marasigeziyorum.viewModel

import androidx.lifecycle.ViewModel
import com.marazanil.marasigeziyorum.data.firebase.AuthRepository

class AuthViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    fun register(email: String, password: String, callback: (Boolean) -> Unit) {
        authRepository.register(email, password, callback)
    }

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        authRepository.login(email, password, callback)
    }

    fun logout() {
        authRepository.logout()
    }
}
