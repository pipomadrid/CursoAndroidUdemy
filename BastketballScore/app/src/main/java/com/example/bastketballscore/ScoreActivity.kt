package com.example.bastketballscore

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ImageView
import android.widget.TextView
import com.example.bastketballscore.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {

    companion object {
        const val LOCAL_SCORE_KEY = "local_score"
        const val VISITOR_SCORE_KEY = "visitor_score"
    }
private lateinit var binding:ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imagen = binding.ivCarita
        val mensaje = binding.tvTextResult

        val  bundle :Bundle = intent.extras!!
        val marcador_local= bundle.getInt(LOCAL_SCORE_KEY)
        val marcador_visitante = bundle.getInt(VISITOR_SCORE_KEY)



        binding.tvLocalScore.text = marcador_local.toString()
        binding.tvVisitorScore.text = marcador_visitante.toString()
        comprobarResultado(imagen,mensaje,marcador_local,marcador_visitante)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun comprobarResultado(Imagen :ImageView, mensaje:TextView, marcador1:Int, marcador2:Int) {

        if(marcador1 > marcador2) {
            mensaje.text = getString(R.string.gana_local)
            Imagen.setImageDrawable(getDrawable(R.drawable.alegre))
        } else if (marcador1<marcador2){
            mensaje.text = getString(R.string.gana_visitante)
            Imagen.setImageDrawable(getDrawable(R.drawable.alegre))
        } else {
            mensaje.text = getString(R.string.empate)
            Imagen.setImageDrawable(getDrawable(R.drawable.triste))
        }
    }
}