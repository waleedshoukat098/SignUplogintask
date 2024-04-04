package com.techinnovation.taskoo.viewmodels

import com.google.firebase.auth.FirebaseAuth
import com.techinnovation.taskoo.model.SignUpModel

class LoginViewModel {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(signUpModel: SignUpModel, onLoginComplete: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(signUpModel.email, signUpModel.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login success
                    onLoginComplete(true, null)
                } else {
                    // Login failed
                    onLoginComplete(false, task.exception?.message)
                }
            }
    }
}
