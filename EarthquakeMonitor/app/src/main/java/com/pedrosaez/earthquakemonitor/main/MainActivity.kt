package com.pedrosaez.earthquakemonitor.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pedrosaez.earthquakemonitor.Earthquake
import com.pedrosaez.earthquakemonitor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // creamos una variable viewmodel para poder usar los métodos de la clase omónima
        val viewModel: MainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        val recyclerView = binding.eqRecycler


        // creamos el observador para el livedata eqList que nos proporcionará la lista de datos que
        // pasaremos al recycler
        viewModel.eqList.observe(this, Observer {

            val adapter = EqAdapter(this,it)
            recyclerView.adapter = adapter
            handleEmptyView(it, binding)
            adapter.onItemClickListener = {
                Toast.makeText(this,it.place, Toast.LENGTH_SHORT).show()
            }
        })
    }

    // método que maneja como se comorta el view cuando la lista está vacia
    private fun handleEmptyView(it: MutableList<Earthquake>, binding:  ActivityMainBinding) {
        if (it.isEmpty()) {
            binding.eqEmptyView.visibility = View.VISIBLE
        } else {
            binding.eqEmptyView.visibility = View.GONE
        }
    }


}