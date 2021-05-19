package com.pedrosaez.earthquakemonitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedrosaez.earthquakemonitor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      val recyclerView = binding.eqRecycler

        val eqList = mutableListOf<Earthquake>()
        eqList.add(Earthquake("1","Buenos Aires", 4.3,2738455252L,-121.2412,213.1244))
        eqList.add(Earthquake("2","Lima", 3.3,2738455252L,-121.2412,213.1244))
        eqList.add(Earthquake("3","Madrid", 1.9,2738455252L,-121.2412,213.1244))
        eqList.add(Earthquake("4","Sevilla", 2.6,2738455252L,-121.2412,213.1244))
        eqList.add(Earthquake("5","Bogotá", 1.3,2738455252L,-121.2412,213.1244))
        eqList.add(Earthquake("6","Cádiz", 3.9,2738455252L,-121.2412,213.1244))
        eqList.add(Earthquake("7","Lleida", 2.6,2738455252L,-121.2412,213.1244))

        recyclerView.adapter = EqAdapter(this, eqList)
        recyclerView.setHasFixedSize(true)

    }




}