package com.pedrosaez.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class listFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.pokemon_recycler)
        val adapter = PokemonAdapter()
        recycler.adapter = adapter


        val pokemonList:MutableList<Pokemon> = mutableListOf(
            Pokemon(1, "Bulbasaur",45,49,49,45 ,Pokemon.PokemonType.GRASS),
            Pokemon(2, "Ivysaur", 60,62,63,60,Pokemon.PokemonType.GRASS),
            Pokemon(3, "Venuasaur",80,82,83,80, Pokemon.PokemonType.GRASS),
            Pokemon(4, "Charmander", 39,52,43,65,Pokemon.PokemonType.FIRE),
            Pokemon(5, "Charmeleon",58,64,58,80,Pokemon.PokemonType.FIRE),
            Pokemon(6, "Charizard",78,84,78,100, Pokemon.PokemonType.FIRE),
            Pokemon(7, "Squirtle", 44,48,65,43,Pokemon.PokemonType.WATER),
            Pokemon(8, "Wartortle",59,63,80,58, Pokemon.PokemonType.WATER)
        )
        adapter.submitList(pokemonList)
        return view
    }


}