package com.github.cesar1287.pingpongx

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.cesar1287.pingpongx.Constants.KEY_RESULT_EXTRA_PLAYER_AWAY_NAME
import com.github.cesar1287.pingpongx.Constants.KEY_RESULT_EXTRA_PLAYER_AWAY_SCORE
import com.github.cesar1287.pingpongx.Constants.KEY_RESULT_EXTRA_PLAYER_HOME_NAME
import com.github.cesar1287.pingpongx.Constants.KEY_RESULT_EXTRA_PLAYER_HOME_SCORE
import com.github.cesar1287.pingpongx.PlayerActivity.Companion.INTENT_PLAYER_AWAY_KEY
import com.github.cesar1287.pingpongx.PlayerActivity.Companion.INTENT_PLAYER_HOME_KEY
import com.github.cesar1287.pingpongx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
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
                viewModel.registerPlayerHomeScore()
            }

            btMainScoreAway.setOnClickListener {
                viewModel.registerPlayerAwayScore()
            }

            btMainRematch.setOnClickListener {
                viewModel.rematch()
            }

            btMainEndGame.setOnClickListener {
                finish()
            }

            btMainShare.setOnClickListener {
                shareWhatsApp()
            }

            viewModel.onPlayerHomeScore.observe(this@MainActivity) { points ->
                setupPlayHomeScore(points.toString())
            }

            viewModel.onPlayerAwayScore.observe(this@MainActivity) { points ->
                setupPlayAwayScore(points.toString())
            }
        }
    }

    private fun shareWhatsApp() {
        try {
            val whatsAppIntent = Intent(Intent.ACTION_SEND)
            whatsAppIntent.type = "text/plain"
            whatsAppIntent.setPackage("com.whatsapp")

            val message = getString(
                R.string.message_to_share,
                binding.tvMainPlayerHome.text.toString(),
                binding.tvMainPlayerAway.text.toString(),
                binding.tvMainScoreHome.text.toString().toInt(),
                binding.tvMainScoreAway.text.toString().toInt()
            )

            whatsAppIntent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(whatsAppIntent)
        } catch (e: ActivityNotFoundException) {
            //Toast.makeText(this, "WhatsApp não instalado", Toast.LENGTH_LONG).show()
            val appPackageName = "com.whatsapp"
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (anfe: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
    }

    private fun getWinner(): String {
        val playerHomeScore = viewModel.onPlayerHomeScore.value ?: 0
        val playerAwayScore = viewModel.onPlayerAwayScore.value ?: 0
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

    private fun setupPlayHomeScore(points: String) {
        binding.tvMainScoreHome.text = points
    }

    private fun setupPlayAwayScore(points: String) {
        binding.tvMainScoreAway.text = points
    }

    companion object {
        const val INTENT_WINNER_PLAYER = "winnerPlayer"
    }
}