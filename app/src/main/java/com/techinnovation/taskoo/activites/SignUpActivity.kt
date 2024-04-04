package com.techinnovation.taskoo.activites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.techinnovation.taskoo.databinding.ActivitySignUpBinding
import com.techinnovation.taskoo.model.SignUpModel
import com.techinnovation.taskoo.viewmodels.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by lazy {
        ViewModelProvider(this).get(SignUpViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alreadyacc.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signup.setOnClickListener {
            val email = binding.emailedtextsign.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                // If any field is empty, show a toast and return
                Toast.makeText(this@SignUpActivity, "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
                val signUpModel = SignUpModel(email, password)


                viewModel.signUp(signUpModel) { success, message ->
                    if (success) {
                        Toast.makeText(this@SignUpActivity, "User Created", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                        startActivity(intent)



                        // Optional: Finish SignUpActivity to prevent going back to it with back button
                    } else {
                        Toast.makeText(this@SignUpActivity, "Error: $message", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }

        }
 }
