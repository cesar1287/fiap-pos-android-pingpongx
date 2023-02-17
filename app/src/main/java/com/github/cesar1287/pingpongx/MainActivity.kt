package com.github.cesar1287.pingpongx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.cesar1287.pingpongx.PlayerActivity.Companion.INTENT_PLAYER_AWAY_KEY
import com.github.cesar1287.pingpongx.PlayerActivity.Companion.INTENT_PLAYER_HOME_KEY
import com.github.cesar1287.pingpongx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var playerHomeScore = 0
    private var playerAwayScore = 0

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

        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            btMainScoreHome.setOnClickListener {
                playerHomeScore += 1
                tvMainScoreHome.text = playerHomeScore.toString()
            }

            btMainScoreAway.setOnClickListener {
                playerAwayScore += 1
                tvMainScoreAway.text = playerAwayScore.toString()
            }

            btMainRematch.setOnClickListener {
                playerHomeScore = 0
                playerAwayScore = 0
                rematch()
            }

            btMainEndGame.setOnClickListener {
                finish()
            }
        }
    }

    private fun rematch() {
        with(binding) {
            tvMainScoreHome.text = playerHomeScore.toString()
            tvMainScoreAway.text = playerAwayScore.toString()
        }
    }
}