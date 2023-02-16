package com.github.cesar1287.pingpongx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.github.cesar1287.pingpongx.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAnimation()
    }

    private fun initAnimation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.splash)
        with(binding) {
            ivSplashLogo.startAnimation(anim)
            ivSplashName.startAnimation(anim)
        }
    }

}