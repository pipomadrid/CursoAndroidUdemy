package com.pedrosaez.pokedex

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.lang.ClassCastException

class listFragment : Fragment() {


    interface PokemonSelectedListener{
        fun onPokemonSelected(pokemon:Pokemon)
    }

    private lateinit var pokemonSelectedListener: PokemonSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pokemonSelectedListener = try {
            context as PokemonSelectedListener
        }catch (e:ClassCastException){
            throw ClassCastException("$context must implement PokemonSelectListener")
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.pokemon_recycler)
        val adapter = PokemonAdapter()
        recycler.adapter = adapter
        adapter.onItemClickListener = {

            pokemonSelectedListener.onPokemonSelected(it)
        }

        val pokemonList:MutableList<Pokemon> = mutableListOf(
                Pokemon(1, "Bulbasaur",45,49,49,45 ,Pokemon.PokemonType.GRASS,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0yQXpquVrhAECby-7ts1o7D7wR1MZFPqfAw&usqp=CAU",R.raw.bulbasaur),
                Pokemon(2, "Ivysaur", 60,62,63,60,Pokemon.PokemonType.GRASS,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXB1koE4s0J_l_r2tsZsWG_EpE-h9DMRuX0Q&usqp=CAU",R.raw.ivysaur),
                Pokemon(3, "Venuasaur",80,82,83,80, Pokemon.PokemonType.GRASS,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtN5Ogu1YrCL43JCLAMqKwSyHlFoZP30HT5g&usqp=CAU",R.raw.venuasaur),
                Pokemon(4, "Charmander", 39,52,43,65,Pokemon.PokemonType.FIRE,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR83yoeGYyu9o2-eXqEvCoem3Hbe4NKl3hpTg&usqp=CAU",R.raw.charmander),
                Pokemon(5, "Charmeleon",58,64,58,80,Pokemon.PokemonType.FIRE,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7KcOchhNsntyR087Lpyl0qLzrAjrdhDxaBQ&usqp=CAU",R.raw.charmaleon),
                Pokemon(6, "Charizard",78,84,78,100, Pokemon.PokemonType.FIRE,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ87tWFwsI02LsJh0qHQz-WgPNbm8SFWnob3w&usqp=CAU",R.raw.charizard),
                Pokemon(7, "Squirtle", 44,48,65,43,Pokemon.PokemonType.WATER,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgWgjbwApdGGXPy0xz3EZqMDT_IVLZjSu4iw&usqp=CAU",R.raw.squirtle),
                Pokemon(8, "Wartortle",59,63,80,58, Pokemon.PokemonType.WATER,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRV7Fq5qga-m5-ah5ktIFMrLKPGMSt9eAoJaQ&usqp=CAU",R.raw.wartortle)
        )
        adapter.submitList(pokemonList)
        return view
    }


}