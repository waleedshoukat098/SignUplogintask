package com.techinnovation.taskoo.activites

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.techinnovation.taskoo.databinding.ActivityLoginBinding
import com.techinnovation.taskoo.model.SignUpModel
import com.techinnovation.taskoo.viewmodels.SignUpViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
     val viewModel: SignUpViewModel by lazy {
            ViewModelProvider(this).get(SignUpViewModel::class.java)
        }


        auth = FirebaseAuth.getInstance()

        binding.notaccount.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.loginuser.text.toString()
            val password = binding.signupuser.text.toString()

            // Validate email and password (optional)

            // Create a login model
            val loginModel = SignUpModel(email, password)

            // Authenticate the user using Firebase
            auth.signInWithEmailAndPassword(loginModel.email, loginModel.password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        val intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}
