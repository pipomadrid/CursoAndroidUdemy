package com.pedrosaez.fakebot

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pedrosaez.fakebot.databinding.FakeBotListItemBinding

class FbAdapter(private val context: Context, private val datasetPreguntas: List<Frases>)
    : RecyclerView.Adapter<FbAdapter.FbViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FbAdapter.FbViewHolder {
        val binding: FakeBotListItemBinding = FakeBotListItemBinding.inflate(LayoutInflater.from(parent.context))
        return FbViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FbViewHolder, position: Int) {
        val pregunta: Frases = datasetPreguntas[position]
        holder.bind(pregunta)
    }

    override fun getItemCount(): Int {
        return datasetPreguntas.size
    }

    inner class FbViewHolder(private val binding: FakeBotListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pregunta: Frases) {

            /*
            Si es una pregunta se mostrará el recicler cokmo está, si no es pregunta se mostrará como le ponemos
            , con el texto al principio y en gris
             */
            if (pregunta.isQuestion) {
                binding.recyclerPregunta.text = pregunta.texto
            } else {
                binding.recyclerPregunta.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START)
                binding.recyclerPregunta.setBackgroundColor(ContextCompat.getColor(context, R.color.ligth_grey))
                binding.recyclerPregunta.text = pregunta.texto

            }

        }


    }
}