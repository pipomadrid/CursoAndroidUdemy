package com.pedrosaez.earthquakemonitor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EqAdapter(private val context:Context, private val dataset:List<Earthquake>) : RecyclerView.Adapter<EqAdapter.EqViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EqAdapter.EqViewHolder {
       val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.eq_list_item,parent, false)
        return EqViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: EqAdapter.EqViewHolder, position: Int) {
        val earthquake :Earthquake = dataset[position]
        holder.magnitudText.text = earthquake.magnitude.toString()
        holder.placeText.text = earthquake.place.toString()
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class EqViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val magnitudText  = view.findViewById<TextView>(R.id.eq_magnitude_text)
        val placeText  = view.findViewById<TextView>(R.id.eq_place_text)

    }

}