package com.pedrosaez.registro_superheroes

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pedrosaez.registro_superheroes.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object{
        const val SUPERHERO_KEY = "superhero_name"
        const val BITMAP_KEY= "bitmap"



    }
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle = intent.extras!!
        val superHero: SuperHero= bundle.getParcelable<SuperHero>(SUPERHERO_KEY)!!
        val bitmap: Bitmap = bundle.getParcelable<Bitmap>(BITMAP_KEY)!!

        binding.ivPicture.setImageBitmap(bitmap)
        binding.tvHeroName.text = superHero.superHeroName
        binding.tvBioText.text = superHero.bio
        binding.tvAlterEgoText.text = superHero.alterEgo
        binding.rbPower.rating= superHero.power

    }
}