/*
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import com.techinnovation.taskoo.model.SignUpModel

class viewModel:ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun signUp(signUpModel: SignUpModel, onSignUpComplete: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(signUpModel.email, signUpModel.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign up success
                    val user = auth.currentUser
                    user?.let { currentUser ->
                        // Save user data to Firestore
                        val userData = hashMapOf(
                            "email" to signUpModel.email,
                            // Add more user data fields as needed
                        )
                        db.collection("users").document(currentUser.uid)
                            .set(userData)
                            .addOnSuccessListener {
                                // User data saved successfully
                                onSignUpComplete(true, null)
                            }
                            .addOnFailureListener { e ->
                                // Error saving user data
                                onSignUpComplete(false, e.message)
                            }
                    }
                } else {
                    // Sign up failed
                    onSignUpComplete(false, task.exception?.message)
                }
            }
    }
}/////
var a:Int = 5
*/
