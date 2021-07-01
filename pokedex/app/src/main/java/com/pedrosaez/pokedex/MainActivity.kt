package com.pedrosaez.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController


class MainActivity : AppCompatActivity() , listFragment.PokemonSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPokemonSelected(pokemon: Pokemon) {
      findNavController(R.id.main_navigation_container).navigate(listFragmentDirections.actionListFragmentToPokemonDetailFragment())
    }
}