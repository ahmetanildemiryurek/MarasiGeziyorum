package com.marazanil.marasigeziyorum.data.repo

import com.google.firebase.firestore.FirebaseFirestore
import com.marazanil.marasigeziyorum.data.db.entity.User

class UserRepository {

    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection("users")

    fun registerUser(firstName: String, lastName: String, username: String, password: String, callback: (Boolean) -> Unit) {
        userCollection.whereEqualTo("username", username).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    val userId = userCollection.document().id
                    val user = User(id = userId, firstName = firstName, lastName = lastName, username = username, password = password)
                    userCollection.document(userId).set(user)
                        .addOnCompleteListener { task ->
                            callback(task.isSuccessful)
                        }
                } else {
                    callback(false)
                }
            }
    }

    fun loginUser(username: String, password: String, callback: (Boolean) -> Unit) {
        userCollection.whereEqualTo("username", username).whereEqualTo("password", password).get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val user = documents.documents[0].toObject(User::class.java)
                    callback(user != null)
                } else {
                    callback(false)
                }
            }
    }

    fun getUserDataByUsername(username: String, callback: (User?) -> Unit) {
        userCollection.whereEqualTo("username", username).get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val user = documents.documents[0].toObject(User::class.java)
                    callback(user)
                } else {
                    callback(null)
                }
            }
    }
}
