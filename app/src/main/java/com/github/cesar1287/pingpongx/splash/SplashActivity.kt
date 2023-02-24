package com.github.cesar1287.pingpongx.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.github.cesar1287.pingpongx.player.PlayerActivity
import com.github.cesar1287.pingpongx.R
import com.github.cesar1287.pingpongx.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAnimation()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this@SplashActivity, PlayerActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000L
        )
    }

    private fun initAnimation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.splash)
        with(binding) {
            ivSplashLogo.startAnimation(anim)
            ivSplashName.startAnimation(anim)
        }
    }

}