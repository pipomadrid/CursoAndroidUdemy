package com.pedrosaez.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter :ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Pokemon>(){

        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }


    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val idText = view.findViewById<TextView>(R.id.pokemon_list_item_id_textView)
        private val nameText = view.findViewById<TextView>(R.id.pokemon_list_item_name_textView)
        private val typeImage  = view.findViewById<ImageView>(R.id.pokemon_list_item_type_image)

        fun bind(pokemon: Pokemon){
            idText.text = pokemon.id.toString()
            nameText.text = pokemon.name
            val imageId = when(pokemon.type){
                Pokemon.PokemonType.GRASS -> R.drawable.plant
                Pokemon.PokemonType.FIRE -> R.drawable.fire
                Pokemon.PokemonType.WATER -> R.drawable.water
                Pokemon.PokemonType.ELECTRIC -> R.drawable.electric
            }
            typeImage.setImageResource(imageId)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)
    }

}