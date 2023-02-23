package com.github.cesar1287.pingpongx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.cesar1287.pingpongx.Constants.KEY_RESULT_EXTRA_PLAYER_AWAY_NAME
import com.github.cesar1287.pingpongx.Constants.KEY_RESULT_EXTRA_PLAYER_AWAY_SCORE
import com.github.cesar1287.pingpongx.Constants.KEY_RESULT_EXTRA_PLAYER_HOME_NAME
import com.github.cesar1287.pingpongx.Constants.KEY_RESULT_EXTRA_PLAYER_HOME_SCORE
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

        savedInstanceState?.let {
            playerHomeScore = it.getInt(PLAYER_HOME_SCORE)
            playerAwayScore = it.getInt(PLAYER_AWAY_SCORE)
            setupPlayHomeScore()
            setupPlayAwayScore()
        }

        with(binding) {
            tvMainPlayerHome.text = playerHome
            tvMainPlayerAway.text = playerAway
        }

        setupListeners()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PLAYER_HOME_SCORE, playerHomeScore)
        outState.putInt(PLAYER_AWAY_SCORE, playerAwayScore)
    }

    private fun setupListeners() {
        with(binding) {
            btMainScoreHome.setOnClickListener {
                playerHomeScore += 1
                setupPlayHomeScore()
            }

            btMainScoreAway.setOnClickListener {
                playerAwayScore += 1
                setupPlayAwayScore()
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

    private fun getWinner(): String {
        return if (playerHomeScore > playerAwayScore) {
            binding.tvMainPlayerHome.text.toString()
        } else if (playerAwayScore > playerHomeScore) {
            binding.tvMainPlayerAway.text.toString()
        } else {
            getString(R.string.draw_label)
        }
    }

    override fun finish() {
        val ret = Intent()
        ret.putExtra(INTENT_WINNER_PLAYER, getWinner())
        with(binding) {
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_HOME_NAME, tvMainPlayerHome.text.toString())
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_AWAY_NAME, tvMainPlayerAway.text.toString())
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_HOME_SCORE, tvMainScoreHome.text.toString().toInt())
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_AWAY_SCORE, tvMainScoreAway.text.toString().toInt())
        }
        setResult(RESULT_OK, ret)
        super.finish()
    }


    private fun rematch() {
        setupPlayHomeScore()
        setupPlayAwayScore()
    }

    private fun setupPlayHomeScore() {
        binding.tvMainScoreHome.text = playerHomeScore.toString()
    }

    private fun setupPlayAwayScore() {
        binding.tvMainScoreAway.text = playerAwayScore.toString()
    }

    companion object {
        const val INTENT_WINNER_PLAYER = "winnerPlayer"

        private const val PLAYER_HOME_SCORE = "playerHomeScore"
        private const val PLAYER_AWAY_SCORE = "playerAwayScore"
    }
}