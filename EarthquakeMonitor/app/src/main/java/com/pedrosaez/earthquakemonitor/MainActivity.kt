package com.pedrosaez.earthquakemonitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedrosaez.earthquakemonitor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel:MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val recyclerView = binding.eqRecycler


        viewModel.eqList.observe(this, Observer {

            val adapter = EqAdapter(this,it)
            recyclerView.adapter = adapter
            handleEmptyView(it, binding)
            adapter.onItemClickListener = {
                Toast.makeText(this,it.place, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleEmptyView(it: MutableList<Earthquake>, binding:  ActivityMainBinding) {
        if (it.isEmpty()) {
            binding.eqEmptyView.visibility = View.VISIBLE
        } else {
            binding.eqEmptyView.visibility = View.GONE
        }
    }


}