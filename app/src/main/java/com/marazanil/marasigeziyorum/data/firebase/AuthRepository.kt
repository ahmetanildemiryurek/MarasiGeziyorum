package com.marazanil.marasigeziyorum.data.firebase

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun register(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }

    fun logout() {
        firebaseAuth.signOut()
    }
}

