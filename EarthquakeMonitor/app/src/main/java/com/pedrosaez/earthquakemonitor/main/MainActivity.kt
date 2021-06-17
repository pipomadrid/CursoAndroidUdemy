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
import com.pedrosaez.earthquakemonitor.api.WorkerUtil
import com.pedrosaez.earthquakemonitor.databinding.ActivityMainBinding
import com.pedrosaez.earthquakemonitor.details.DetailsActivity


private const val  SORT_TYPE_KEY = "sort_type"
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sortType = getSortType()

        WorkerUtil.scheduleSync(this)

        // creamos una variable viewmodel para poder usar los métodos de la clase omónima
        viewModel = ViewModelProvider(this,
            MainViewModelFactory(application,sortType )).get(MainViewModel::class.java)
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

    private fun getSortType(): Boolean {

        val prefs = getPreferences(MODE_PRIVATE)
        return prefs.getBoolean(SORT_TYPE_KEY,false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if(itemId == R.id.main_menu_sort_magnitude){
            viewModel.realoadEarthquakesFromDatabase(true)
            saveSortType(true)
        }else if(itemId == R.id.main_menu_sort_time) {
            viewModel.realoadEarthquakesFromDatabase(false)
            saveSortType(false)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun  saveSortType(sortByMagnitude :Boolean){

        val prefs = getPreferences( MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean(SORT_TYPE_KEY,sortByMagnitude)
        editor.apply()


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