package com.example.filmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        @Suppress("DEPRECATION")
        Handler().postDelayed({
            val splashIntent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(splashIntent)
            finish()
        },2000)
    }
}