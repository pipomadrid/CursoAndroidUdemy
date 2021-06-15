package com.pedrosaez.earthquakemonitor.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pedrosaez.earthquakemonitor.Earthquake
import com.pedrosaez.earthquakemonitor.R
import com.pedrosaez.earthquakemonitor.api.ApiResponseStatus
import com.pedrosaez.earthquakemonitor.databinding.ActivityMainBinding
import com.pedrosaez.earthquakemonitor.details.DetailsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // creamos una variable viewmodel para poder usar los métodos de la clase omónima
        viewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        val recyclerView = binding.eqRecycler


        // creamos el observador para el livedata eqList que nos proporcionará la lista de datos que
        // pasaremos al recycler
        viewModel.eqList.observe(this, Observer {

            val adapter = EqAdapter(this,it)
            recyclerView.adapter = adapter
            handleEmptyView(it, binding)
            adapter.onItemClickListener = {
              openDetailActivity(it)
            }
        })
        viewModel.status.observe(this, Observer {
            if(it == ApiResponseStatus.LOADING ){
                binding.loadingWheel.visibility = View.VISIBLE

            }else if (it == ApiResponseStatus.DONE ){
                binding.loadingWheel.visibility = View.GONE

            }else if (it == ApiResponseStatus.ERROR )
                binding.loadingWheel.visibility = View.GONE

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if(itemId == R.id.main_menu_sort_magnitude){
            viewModel.realoadEarthquakesFromDatabase(true)
        }else if(itemId == R.id.main_menu_sort_time) {
            viewModel.realoadEarthquakesFromDatabase(false)
        }
        return super.onOptionsItemSelected(item)
    }

    // método que maneja como se comorta el view cuando la lista está vacia
    private fun handleEmptyView(it: MutableList<Earthquake>, binding:  ActivityMainBinding) {
        if (it.isEmpty()) {
            binding.eqEmptyView.visibility = View.VISIBLE
        } else {
            binding.eqEmptyView.visibility = View.GONE
        }
    }
    private fun openDetailActivity(earthquake: Earthquake) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EQ_KEY, earthquake)
        startActivity(intent)
    }



}