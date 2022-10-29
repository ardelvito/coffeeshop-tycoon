package com.example.coffeeshoptycoon_160420030

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_preparation.txtDayResult
import kotlinx.android.synthetic.main.activity_preparation.txtWeatherSimulation
import kotlinx.android.synthetic.main.activity_simulation.*

class SimulationActivity : AppCompatActivity() {
    companion object {
        val DAY = "CURRENTDAY"
        val SELLINGCUP = "CURRENTSELLINGCUP"
        val WEATHER = "CURRENTWEATHER"
        val GRANDTOTAL = "GRANDTOTAL"
        val SELLINGPRICE = "SELLINGPRICE"
        val BALANCE = "BALANCE"
        val PLAYERNAME = "PLAYERNAME"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)

        //Menerima value dari PreparationActivity
        val playerName = intent.getStringExtra(PLAYERNAME)
        val currDay = intent.getIntExtra(PreparationActivity.DAY, 0)
        val currWeather = intent.getStringExtra(PreparationActivity.WEATHER)
        var currSellCup = intent.getIntExtra(PreparationActivity.SELLINGCUP, 0)

        val currGrandTotal = intent.getIntExtra(PreparationActivity.GRANDTOTAL, 0)
        val currSellPrice = intent.getIntExtra(PreparationActivity.SELLINGPRICE, 0)

        val currBalance = intent.getIntExtra(PreparationActivity.BALANCE, 0)
        Log.d("Grand Total", currGrandTotal.toString())
        Log.d("Selling Price per Cup", currSellPrice.toString())



        txtDayResult.text = "Day " + currDay.toString()
        txtWeatherSimulation.text = currWeather.toString()

        val hours = ArrayList<String>()
        var totalSold = 0
        var stock = currSellCup
        var status = ""
        var start = 0
        for (i in 7..18) {
            var randomSelling = (start..stock).random()
            totalSold += randomSelling
            Log.d("Stock Awal ", stock.toString())

            Log.d("Terjual ", randomSelling.toString())

            stock = stock - randomSelling
            Log.d("Stock Tersisa ", stock.toString())

            if (randomSelling == 0){

                if(stock > 0){
                    status = "No Customer"
                    Log.d("Status", "No Customer")
                }
                else{
                    status = "Out of stock"
                }

            }
            else{
                status = randomSelling.toString()

            }


            if(i < 10){
                val i = "0"+i.toString()+".00 " + status

                hours.add(i)

            }
            else{
                val i = i.toString()+".00 " + status

                hours.add(i)
            }
//            hours.add(i.toString())
        }
//        print(hours)
        Log.d("Sold", totalSold.toString())
        currSellCup = totalSold
        val adapter = HourAdapter(hours)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter

        btnResults.setOnClickListener{

            val intent = Intent(this, ResultActivity::class.java)

            intent.putExtra(SELLINGCUP, currSellCup)
            intent.putExtra(WEATHER, currWeather)
            intent.putExtra(DAY, currDay)

            intent.putExtra(SELLINGPRICE, currSellPrice)
            intent.putExtra(GRANDTOTAL, currGrandTotal)

            intent.putExtra(BALANCE, currBalance)

            intent.putExtra(PLAYERNAME, playerName)

            Log.d("Jumlah Cup Terjual", currSellCup.toString())
            Log.d("Cuaca", currWeather.toString())
            Log.d("Hari", currDay.toString())
            Log.d("Harga Jual Per Cup", currSellPrice.toString())
            Log.d("Grand Total", currGrandTotal.toString())
            Log.d("Saldo Sekarang", currBalance.toString())
            Log.d("Nama Player", playerName.toString())
            startActivity(intent)
        }


    }
    override fun onBackPressed() {
        return
    }
}