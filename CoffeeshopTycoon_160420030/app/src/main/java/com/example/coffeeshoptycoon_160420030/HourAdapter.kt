package com.example.coffeeshoptycoon_160420030

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_hour.view.*

class HourAdapter(
    private val listHour: List<String>

): RecyclerView.Adapter<HourAdapter.HourViewHolder>(){
    class HourViewHolder(val view: View):RecyclerView.ViewHolder(view){
        val textVaL = view.findViewById<TextView>(R.id.textViewHour)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        return HourViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_hour, parent, false)

        )
    }

    //set data
    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        holder.textVaL.text = listHour[position]
    }

    override fun getItemCount() = listHour.size

}