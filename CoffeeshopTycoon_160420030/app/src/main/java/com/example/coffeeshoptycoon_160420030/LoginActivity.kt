package com.example.coffeeshoptycoon_160420030

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    companion object {
        val PLAYERNAME = "PLAYERNAME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Shared object untuk menyimpan username, password, dan player name

        var sharedFile = "com.example.coffeeshoptycoon_160420030"
        var shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        var editor:SharedPreferences.Editor = shared.edit()
        editor.putString("username", "v")
        editor.putString("password", "v")
        editor.putString("playername", "Ardel Vito")
        editor.apply()

        btnLogin.setOnClickListener{
            var prefs = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
            var username = prefs.getString("username", "") //"No name defined" is the default value.
            var password = prefs.getString("password", "") //0 is the default value.
            var playername = prefs.getString("playername", "") //"No name defined" is the default value.


            if (txtUsername.text.toString() == username && txtPassword.text.toString() == password){
                Toast.makeText(this, "Hi $playername", Toast.LENGTH_SHORT).show()

                //Ketika button login diklik dan login benar maka
                // menuju ke PreparationActivity untuk bermain
                val intent = Intent(this, PreparationActivity::class.java)

                //mengirim value ke Preparation Activity yang bernilai Ardel Vito
                //dengan key PLAYERNAME
                intent.putExtra(PLAYERNAME, playername)
                startActivity(intent)

            }
            else{
                Toast.makeText(this, "Username atau password salah!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onBackPressed() {
        return
    }


}