package com.farhan.stickynotesinkotlin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Enable edge-to-edge layout
        setContentView(R.layout.activity_splash_screen)

        // Apply edge-to-edge layout to the main view
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets  // Return the insets to continue the normal insets handling process
        }

        // Delay and start MainActivity after a specified time
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_DELAY_MILLIS)  // Adjust the delay time as needed

        // Optionally handle if the user taps the screen to skip the delay
        findViewById<View>(R.id.main).setOnClickListener {
            Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    companion object {
        private const val SPLASH_DELAY_MILLIS = 2000L  // Splash screen delay in milliseconds
    }
}
