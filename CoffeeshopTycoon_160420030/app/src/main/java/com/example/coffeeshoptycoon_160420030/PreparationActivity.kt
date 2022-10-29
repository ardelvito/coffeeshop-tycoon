package com.example.coffeeshoptycoon_160420030

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_preparation.*


class PreparationActivity : AppCompatActivity() {

    companion object {
        val DAY = "CURRENTDAY"
        val SELLINGCUP = "CURRENTSELLINGCUP"
        val GRANDTOTAL = "GRANDTOTAL"
        val SELLINGPRICE = "SELLINGPRICE"
        val BALANCE = "BALANCE"
        val PLAYERNAME ="PLAYERNAME"
        val WEATHER = "WEATHER"

    }

    var priceRentLocation = 0
    var calculateCapitalPrice = 0
    var grandTotal = 0
    var day: Int = 1
    var weather = ""
    var sellingCup: Int = 0
    var balance: Int = 350000
    var sellingPrice: Int = 0
    var playerName = ""

    val weathers = arrayOf(
        Weather("Rainny"),
        Weather("Thunder"),
        Weather("Sunny")
    )

    var randomWeather = (0..2).random()

    fun calculatePrice(currentMilk: Int, currentCoffee: Int, currentWater: Int): Int {
        var milkPrice = 1000 * currentMilk
        var coffeePrice = 500 * currentCoffee
        var waterPrice = 200 * currentWater

        return milkPrice + coffeePrice + waterPrice
    }

    fun boldFormatter(coffee: String): String {
        return "Cost per cup of coffee is <b><u>" + coffee + "</u></b> "
    }

    fun balanceChecker(balance: Int, grandTotal: Int){

        if(grandTotal > balance){
            btnStartDay.setEnabled(false)

//            Toast.makeText(this, "Saldo Tidak Cukup!", Toast.LENGTH_SHORT).show()

            Log.d("Check Button: ", "Saldo tidak cukup")
        }
        else{
            btnStartDay.setEnabled(true)
            Log.d("Check Button: ", "Saldo cukup")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preparation)

        //Menerima value dari ResultActivity(untuk next day)
        val nextDay = intent.getIntExtra(ResultActivity.DAY, day)
        day = nextDay
        txtDayResult.text = "Day " + day.toString()

        playerName = intent.getStringExtra(PLAYERNAME).toString()

        val updatedBalance = intent.getIntExtra(ResultActivity.BALANCE, balance)
        balance = updatedBalance


//        capitalChecker(calculateCapitalPrice, grandTotal)
        if(balance < 0){
            val intent = Intent(this, GameOverActivity::class.java)

            Log.d("Balance" ,"saldo negatif")

            intent.putExtra(PLAYERNAME, playerName)
            startActivity(intent)

        }
        else{
            txtBalance.text = "Rp " + balance.toString()

            Log.d("Balance" ,"saldo positif")

//            playerName = intent.getStringExtra(PLAYERNAME).toString()
            txtPlayerName.text = "Welcome, $playerName"


            //Set Up Spinner
            val adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,
                Global.locations)
            adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1)

            //attached
            spinnerLocations.adapter = adapter

            randomWeather = (0..2).random()
            weathers.shuffle()

//        Log("tag", weathers[0])
//        Log.d("myTag", weathers.size.toString())
//        Log.d("Random", randomWeather.toString())

            weather = weathers[randomWeather].weatherType
            txtWeatherSimulation.text = weathers[randomWeather].weatherType

            //Set angka 1 sebagai default
            txtTotalCoffee.setText(1.toString())
            txtTotalWater.setText(1.toString())
            txtTotalMilk.setText(1.toString())

            txtSell.setText((1.toString()))
            txtSellingPrice.setText(1.toString())

            var currentCoffee = Integer.parseInt(txtTotalCoffee.text.toString())
            var currentWater = Integer.parseInt(txtTotalWater.text.toString())
            var currentMilk = Integer.parseInt(txtTotalMilk.text.toString())

            //panggil method untuk menghitung harga
            var costPerCup = calculatePrice(currentMilk, currentCoffee,currentWater)
            //set hasil ke text
            txtPricePerCup.setText(Html.fromHtml(boldFormatter(costPerCup.toString())))

            txtCupOfCoffee.text = txtSell.text.toString() + " Cup of Cofee"
            txtCapitalPerCoffee.text = "Rp 1"

            grandTotal = calculateCapitalPrice + priceRentLocation
            txtGrandTotal.text = "Rp " + grandTotal.toString()

            sellingCup = Integer.parseInt(txtSell.text.toString())
            sellingPrice = Integer.parseInt(txtSellingPrice.text.toString())
            calculateCapitalPrice = sellingCup * costPerCup

            txtCapitalCoffee.text = "Rp " + calculateCapitalPrice.toString()

            btnAddCoffee.setOnClickListener{


                //tambah
                currentCoffee ++

                //panggil method untuk menghitung harga
                var costPerCup = calculatePrice(currentMilk, currentCoffee,currentWater)
                //set hasil ke text
                txtPricePerCup.setText(Html.fromHtml(boldFormatter(costPerCup.toString())))

                //Set value ke txtTotalCoffee dalam bentuk string
                txtTotalCoffee.setText(currentCoffee.toString())

                sellingCup = Integer.parseInt(txtSell.text.toString())
                calculateCapitalPrice = sellingCup * costPerCup

                txtCapitalCoffee.text = "Rp " + calculateCapitalPrice.toString()

                grandTotal = calculateCapitalPrice + priceRentLocation
                txtGrandTotal.text = "Rp " + grandTotal.toString()

                txtCapitalPerCoffee.setText("Rp " + costPerCup.toString())

//                balanceChecker(balance, grandTotal)


                Log.d("price", calculateCapitalPrice.toString())

                Log.d("grand total", grandTotal.toString())

//                capitalChecker(calculateCapitalPrice, grandTotal)

            }

            btnSubtractCoffee.setOnClickListener{

                if(currentCoffee != 1){
                    currentCoffee --
                }
                var costPerCup = calculatePrice(currentMilk, currentCoffee,currentWater)

                txtPricePerCup.setText(Html.fromHtml(boldFormatter(costPerCup.toString())))

                txtTotalCoffee.setText(currentCoffee.toString())

                sellingCup = Integer.parseInt(txtSell.text.toString())
                calculateCapitalPrice = sellingCup * costPerCup

                txtCapitalCoffee.text = "Rp " + calculateCapitalPrice.toString()

                grandTotal = calculateCapitalPrice + priceRentLocation
                txtGrandTotal.text = "Rp " + grandTotal.toString()

                txtCapitalPerCoffee.setText("Rp " + costPerCup.toString())

                balanceChecker(balance, grandTotal)


                Log.d("price", calculateCapitalPrice.toString())

                Log.d("grand total", grandTotal.toString())

//                capitalChecker(calculateCapitalPrice, grandTotal)




            }

            btnAddWater.setOnClickListener{

                currentWater++

                var costPerCup = calculatePrice(currentMilk, currentCoffee,currentWater)

                txtPricePerCup.setText(Html.fromHtml(boldFormatter(costPerCup.toString())))

                txtTotalWater.setText(currentWater.toString())

                sellingCup = Integer.parseInt(txtSell.text.toString())
                calculateCapitalPrice = sellingCup * costPerCup

                txtCapitalCoffee.text = "Rp " + calculateCapitalPrice.toString()

                grandTotal = calculateCapitalPrice + priceRentLocation
                txtGrandTotal.text = "Rp " + grandTotal.toString()

                txtCapitalPerCoffee.setText("Rp " + costPerCup.toString())

                balanceChecker(balance, grandTotal)



                Log.d("Harga Modal", calculateCapitalPrice.toString())

                Log.d("grand total", grandTotal.toString())

//                capitalChecker(sellingPrice*sellingCup, grandTotal)


            }

            btnSubtractWater.setOnClickListener{

                if(currentWater != 1){
                    currentWater --
                }
                var costPerCup = calculatePrice(currentMilk, currentCoffee,currentWater)

                txtPricePerCup.setText(Html.fromHtml(boldFormatter(costPerCup.toString())))

                //Set value ke txtTotalCoffee dalam bentuk string
                txtTotalWater.setText(currentWater.toString())

                sellingCup = Integer.parseInt(txtSell.text.toString())
                calculateCapitalPrice = sellingCup * costPerCup

                txtCapitalCoffee.text = "Rp " + calculateCapitalPrice.toString()

                grandTotal = calculateCapitalPrice + priceRentLocation
                txtGrandTotal.text = "Rp " + grandTotal.toString()

                txtCapitalPerCoffee.setText("Rp " + costPerCup.toString())

                balanceChecker(balance, grandTotal)


                Log.d("price", calculateCapitalPrice.toString())

                Log.d("grand total", grandTotal.toString())

//                capitalChecker(sellingPrice*sellingCup, grandTotal)

            }

            btnAddMilk.setOnClickListener{

                currentMilk ++

                var costPerCup = calculatePrice(currentMilk, currentCoffee,currentWater)

                txtPricePerCup.setText(Html.fromHtml(boldFormatter(costPerCup.toString())))


                //Set value ke txtTotalCoffee dalam bentuk string
                txtTotalMilk.setText(currentMilk.toString())

                sellingCup = Integer.parseInt(txtSell.text.toString())
                calculateCapitalPrice = sellingCup * costPerCup

                txtCapitalCoffee.text = "Rp " + calculateCapitalPrice.toString()

                grandTotal = calculateCapitalPrice + priceRentLocation
                txtGrandTotal.text = "Rp " + grandTotal.toString()

                txtCapitalPerCoffee.setText("Rp " + costPerCup.toString())

                balanceChecker(balance, grandTotal)


                Log.d("price", calculateCapitalPrice.toString())

                Log.d("grand total", grandTotal.toString())

//                capitalChecker(sellingPrice*sellingCup, grandTotal)

            }

            btnSubtractMilk.setOnClickListener{

                if(currentMilk != 1){
                    currentMilk --
                }

                var costPerCup = calculatePrice(currentMilk, currentCoffee,currentWater)

                txtPricePerCup.setText(Html.fromHtml(boldFormatter(costPerCup.toString())))

                //Set value ke txtTotalCoffee dalam bentuk string
                txtTotalMilk.setText(currentMilk.toString())

                sellingCup = Integer.parseInt(txtSell.text.toString())
                calculateCapitalPrice = sellingCup * costPerCup

                txtCapitalCoffee.text = "Rp " + calculateCapitalPrice.toString()

                grandTotal = calculateCapitalPrice + priceRentLocation
                txtGrandTotal.text = "Rp " + grandTotal.toString()

                txtCapitalPerCoffee.setText("Rp " + costPerCup.toString())

                balanceChecker(balance, grandTotal)


                Log.d("price", calculateCapitalPrice.toString())

                Log.d("grand total", grandTotal.toString())

//                capitalChecker(sellingPrice*sellingCup, grandTotal)

            }

            //Setiap ganti value dari txtSell(jumlah kopi yg mau dijual), akan langsung diambil dan diganti di card
            val editTextSell = findViewById(R.id.txtSell) as EditText
            val cupOfCoffee = findViewById(R.id.txtCupOfCoffee) as TextView

            val capitalCofee = findViewById(R.id.txtCapitalCoffee) as TextView
            editTextSell.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int)
                {

                }

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int)
                {

                    var costPerCup = calculatePrice(currentMilk, currentCoffee,currentWater)
//                Log.d("myTag", costPerCup.toString())


                    //Total modal, modal per cup * jumlah kopi
                    if(!txtSell.text.isEmpty()){
                        Log.d("myTag", "Text terisi!")
                        cupOfCoffee.setText(s.toString() + " Cup of Cofee")

                        sellingCup = Integer.parseInt(s.toString())
                        calculateCapitalPrice = sellingCup * costPerCup

                        capitalCofee.setText("Rp " + calculateCapitalPrice.toString())
                        grandTotal = calculateCapitalPrice + priceRentLocation
                        txtGrandTotal.text = "Rp " + grandTotal.toString()

                        balanceChecker(balance, grandTotal)


                        Log.d("price", calculateCapitalPrice.toString())

                        Log.d("grand total", grandTotal.toString())

//                        capitalChecker(sellingPrice*sellingCup, grandTotal)

                    }


                }
            })

            //Setiap ganti value dari txtSellingPrice, akan langsung diambil dan diganti di card
            val editTextSellingPrice = findViewById(R.id.txtSellingPrice) as EditText
            val pricePerCoffee = findViewById(R.id.txtCapitalPerCoffee) as TextView

            editTextSellingPrice.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int)
                {

                }

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int)
                {

                    if(!txtSellingPrice.text.isEmpty()){
                        sellingPrice = Integer.parseInt(txtSellingPrice.text.toString())
//                        pricePerCoffee.setText("@" + s.toString())
                        grandTotal = calculateCapitalPrice + priceRentLocation
                        txtGrandTotal.text = "Rp " + grandTotal.toString()
                        balanceChecker(balance, grandTotal)

                        Log.d("price", calculateCapitalPrice.toString())

                        Log.d("grand total", grandTotal.toString())

//                        capitalChecker(sellingPrice*sellingCup, grandTotal)



                    }


                }
            })

            //Setiap ganti value dari spinner location, akan diambil dan diganti ke card
            spinnerLocations.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    if (Global.locations[position].toString() == "Business District"){
//                    Log.d("myTag", Global.locations[position].toString() + "350Rb")
                        txtLocation.text = "Business District"
                        priceRentLocation =350000
                    }
                    else if (Global.locations[position].toString() == "University"){
                        txtLocation.text = "University"
                        priceRentLocation = 100000
                    }
                    else{
                        txtLocation.text = "Beach"
                        priceRentLocation = 500000
                    }
                    txtRentPrice.text = "Rp " + priceRentLocation.toString()

                    grandTotal = calculateCapitalPrice + priceRentLocation
                    txtGrandTotal.text = "Rp " + grandTotal.toString()

                    balanceChecker(balance, grandTotal)
                }

            }
            btnStartDay.setOnClickListener{

                //menuju dan mengirim value ke SimulationActivity

                balanceChecker(balance, grandTotal)
                val intent = Intent(this, SimulationActivity::class.java)

                intent.putExtra(SELLINGCUP, sellingCup)
                intent.putExtra(DAY, day)
                intent.putExtra(SELLINGPRICE, sellingPrice)
                intent.putExtra(GRANDTOTAL,grandTotal)
                intent.putExtra(WEATHER, weather)
                intent.putExtra(PLAYERNAME, playerName)
                balance -= grandTotal
                intent.putExtra(BALANCE, balance)
                Log.d("Sisa Saldo", balance.toString())

                startActivity(intent)

            }

        }

    }

    override fun onBackPressed() {
        return
    }

}