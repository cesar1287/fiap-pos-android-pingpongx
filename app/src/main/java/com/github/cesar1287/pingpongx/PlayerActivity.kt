package com.github.cesar1287.pingpongx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.cesar1287.pingpongx.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private val binding: ActivityPlayerBinding by lazy {
        ActivityPlayerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btPlayerStartGame.setOnClickListener {
                val intent = Intent(this@PlayerActivity, MainActivity::class.java)
                intent.putExtra(INTENT_PLAYER_HOME_KEY, etPlayerHome.text.toString())
                intent.putExtra(INTENT_PLAYER_AWAY_KEY, etPlayerAway.text.toString())
                startActivity(intent)
            }
        }
    }

    companion object {
        const val INTENT_PLAYER_HOME_KEY = "playerHome"
        const val INTENT_PLAYER_AWAY_KEY = "playerAway"
    }
}