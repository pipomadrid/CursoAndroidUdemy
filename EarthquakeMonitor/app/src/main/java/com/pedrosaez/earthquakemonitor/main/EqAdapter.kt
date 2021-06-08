package com.pedrosaez.earthquakemonitor.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pedrosaez.earthquakemonitor.Earthquake
import com.pedrosaez.earthquakemonitor.R
import com.pedrosaez.earthquakemonitor.databinding.EqListItemBinding

private val TAG = EqAdapter::class.java.simpleName

class EqAdapter(private val context:Context, private val dataset:List<Earthquake>) : RecyclerView.Adapter<EqAdapter.EqViewHolder> (){


    lateinit var onItemClickListener:(Earthquake) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EqAdapter.EqViewHolder {
        val binding :EqListItemBinding = EqListItemBinding.inflate(LayoutInflater.from(parent.context))
        return EqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EqAdapter.EqViewHolder, position: Int) {
        val earthquake :Earthquake = dataset[position]
        holder.bind(earthquake)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class EqViewHolder(private val binding: EqListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(earthquake: Earthquake){
            binding.eqMagnitudeText.text = context.getString(R.string.magnitude_format, earthquake.magnitude)
            binding.eqPlaceText.text = earthquake.place
            binding.root.setOnClickListener {
                if(::onItemClickListener.isInitialized) {
                    onItemClickListener(earthquake)
                }else{
                    Log.e(TAG , "onItemClickListener not initialized")
                }

            }

        }

    }

}