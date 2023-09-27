package com.companysilas.pokemon.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.companysilas.pokemon.databinding.ActivitySplashBinding
import com.companysilas.pokemon.presentation.pokemon.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.imageLogo.setOnClickListener {
        //    startActivity(Intent(this, MainActivity::class.java))
        //}

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, TIME_SPLASH.toLong())
    }

    companion object {
        const val TIME_SPLASH = 1500
    }

}
