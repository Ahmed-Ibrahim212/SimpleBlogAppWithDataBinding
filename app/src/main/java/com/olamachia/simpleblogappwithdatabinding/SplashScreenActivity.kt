package com.olamachia.simpleblogappwithdatabinding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.olamachia.simpleblogappwithdatabinding.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private var _binding: ActivitySplashScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.bbcNews.alpha = 0f
        binding.bbcNews.animate().setDuration(1500).alpha(1f).withEndAction {
            val user = Intent(this, MainActivity::class.java)
            startActivity(user)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}