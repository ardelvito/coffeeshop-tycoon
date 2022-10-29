package com.example.coffeeshoptycoon_160420030

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    companion object {
        val DAY = "CURRENTDAY"
        val BALANCE = "BALANCE"
        val PLAYERNAME = "PLAYERNAME"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //Menerima value dari IntroActivity
        val playerName = intent.getStringExtra(PLAYERNAME)
        var currDay = intent.getIntExtra(PreparationActivity.DAY, 0)
        val currSellCup = intent.getIntExtra(PreparationActivity.SELLINGCUP, 0)

        val currGrandTotal = intent.getIntExtra(PreparationActivity.GRANDTOTAL, 0)
        val currSellPrice = intent.getIntExtra(PreparationActivity.SELLINGPRICE, 0)

        val totalIncome = currSellPrice * currSellCup
        val profit = totalIncome - currGrandTotal

        var currBalance = intent.getIntExtra(PreparationActivity.BALANCE, 0)

        currBalance += profit


        Log.d("Updated Balance ", currBalance.toString())
        txtDayResult.setText("Day " + currDay.toString())
        currDay += 1

        txtCupCoffeeResult.setText("Day "+currSellCup.toString() + "cup of coffee")
        txtPricePerCupResult.setText("@Rp "+currSellPrice.toString())
        txtIncome.setText("Rp " + totalIncome.toString())

        txtCapitalResult.setText(currGrandTotal.toString())

        txtProfitResult.setText("Rp " + profit.toString())

        Log.d("Total Income ", totalIncome.toString())
        Log.d("Profit ", profit.toString())

        btnStartNewDay.setOnClickListener{

            val intent = Intent(this, PreparationActivity::class.java)

            intent.putExtra(ResultActivity.DAY, currDay)
            intent.putExtra(ResultActivity.BALANCE, currBalance)
            intent.putExtra(ResultActivity.PLAYERNAME, playerName)

            Log.d("Name ", playerName.toString())

            startActivity(intent)
        }


    }
    override fun onBackPressed() {
        return
    }
}