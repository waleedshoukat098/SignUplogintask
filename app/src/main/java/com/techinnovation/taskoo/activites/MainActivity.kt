package com.techinnovation.taskoo.activites

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.techinnovation.taskoo.databinding.ActivityMainBinding

import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isButtonsVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.run {
            button.visibility = View.GONE
            button2.visibility = View.GONE

            imageView.setOnClickListener {
                toggleButtonsVisibilty()
            }
        }
        binding.button.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }


        binding.button2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun toggleButtonsVisibilty() {
        if (isButtonsVisible) {
            hideButtons()
            moveArrowDown()
        } else {
            showButtons()
            moveArrowUp()
        }
        isButtonsVisible = !isButtonsVisible
    }

    private fun showButtons() {
        binding.button.visibility = View.VISIBLE
        binding.button2.visibility = View.VISIBLE
    }

    private fun hideButtons() {
        binding.button.visibility = View.GONE
        binding.button2.visibility = View.GONE
    }

    private fun moveArrowUp() {
        val animator = ObjectAnimator.ofFloat(
            binding.imageView,
            "translationY",
            -100f
        ).apply {
            duration = 500 // Set duration of the animation
            interpolator =
                AccelerateDecelerateInterpolator() // Set interpolator for smooth animation
        }

        val alphaAnimator = ObjectAnimator.ofFloat(
            binding.imageView,
            "alpha",
            0f
        ).apply {
            duration = 500 // Set duration of the animation
            interpolator =
                AccelerateDecelerateInterpolator() // Set interpolator for smooth animation
        }

        // Start both animations together
        AnimatorSet().apply {
            playTogether(animator, alphaAnimator)
            start()
        }
    }

    private fun moveArrowDown() {
        val animator = ObjectAnimator.ofFloat(
            binding.imageView,
            "translationY",
            0f
        )
        animator.apply {
            duration = 500 // Set duration of the animation
            interpolator =
                AccelerateDecelerateInterpolator() // Set interpolator for smooth animation
            start() // Start the animation
        }
    }


}
