package com.yum.tummly.presentation.splash.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.yum.tummly.MainActivity
import com.yum.tummly.R
import com.yum.tummly.databinding.ActivitySplashBinding


/**
 * Splash screen is meant for some app initialization work, will use a view model as and when necessary
 */
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        makeFullScreen()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        Glide.with(applicationContext)
            .asBitmap()
            .load(SPLASH_IMAGE_URL)
            .centerInside()
            .into(binding.splashImg)

        // Using a handler to delay loading the MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // Start activity

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish()
            startActivity(intent)

            // Animate the loading of new activity
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }, SPLASH_SCREEN_DELAY)

    }


    /**
     * Make the activity full screen
     */
    private fun makeFullScreen() {
        // Remove Title
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            // Hide the toolbar
            supportActionBar?.hide()
        }
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY = 3000L
        private const val SPLASH_IMAGE_URL =
            "https://hawkerfresh.s3.ap-south-1.amazonaws.com/tummly/splash.png"
    }
}