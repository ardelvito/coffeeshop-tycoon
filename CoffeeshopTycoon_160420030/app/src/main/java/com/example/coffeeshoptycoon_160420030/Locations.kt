package com.example.coffeeshoptycoon_160420030

data class Locations(val id:Int, val name:String){
    override fun toString(): String{
        return name
    }
}