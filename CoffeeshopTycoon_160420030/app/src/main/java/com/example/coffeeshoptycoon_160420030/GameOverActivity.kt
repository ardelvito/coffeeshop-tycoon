package com.example.coffeeshoptycoon_160420030

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val playerName = intent.getStringExtra(PreparationActivity.PLAYERNAME)

        Log.d("Name", playerName.toString())
        Log.d("Name", "test")

        btnStartDay.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)


            startActivity(intent)
        }

        btnPlayAgain.setOnClickListener{
            val intent = Intent(this, PreparationActivity::class.java)

            intent.putExtra(ResultActivity.PLAYERNAME, playerName)

            startActivity(intent)
        }
    }
}