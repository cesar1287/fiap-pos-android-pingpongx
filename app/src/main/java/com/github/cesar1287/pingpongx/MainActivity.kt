package com.github.cesar1287.pingpongx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.cesar1287.pingpongx.PlayerActivity.Companion.INTENT_PLAYER_AWAY_KEY
import com.github.cesar1287.pingpongx.PlayerActivity.Companion.INTENT_PLAYER_HOME_KEY
import com.github.cesar1287.pingpongx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val playerHome = intent.getStringExtra(INTENT_PLAYER_HOME_KEY)
        val playerAway = intent.getStringExtra(INTENT_PLAYER_AWAY_KEY)

        with(binding) {
            tvMainPlayerHome.text = playerHome
            tvMainPlayerAway.text = playerAway
        }
    }
}