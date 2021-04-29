package com.example.bastketballscore

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
        val marcador_local:String?= bundle.getString(LOCAL_SCORE_KEY)
        val marcador_visitante:String? = bundle.getString(VISITOR_SCORE_KEY)

        val marcador_local_int = marcador_local!!.toInt()
        val marcador_visitante_int = marcador_visitante!!.toInt()

        binding.tvLocalScore.text = Editable.Factory.getInstance().newEditable(marcador_local)
        binding.tvVisitorScore.text = Editable.Factory.getInstance().newEditable(marcador_visitante)
        comprobarResultado(imagen,mensaje,marcador_local_int,marcador_visitante_int)
    }

    fun comprobarResultado(Imagen :ImageView,mensaje:TextView,marcador1:Int,marcador2:Int) {

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