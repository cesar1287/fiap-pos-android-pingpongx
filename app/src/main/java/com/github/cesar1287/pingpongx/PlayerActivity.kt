package com.github.cesar1287.pingpongx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.cesar1287.pingpongx.MainActivity.Companion.INTENT_WINNER_PLAYER
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
                startActivityForResult(intent, WINNER_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == WINNER_REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(
                this,
                getString(R.string.winner_placeholder, data?.getStringExtra(INTENT_WINNER_PLAYER)),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        const val INTENT_PLAYER_HOME_KEY = "playerHome"
        const val INTENT_PLAYER_AWAY_KEY = "playerAway"

        private const val WINNER_REQUEST_CODE = 999
    }
}