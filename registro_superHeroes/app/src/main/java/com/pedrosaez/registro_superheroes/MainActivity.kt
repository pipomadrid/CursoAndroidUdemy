package com.pedrosaez.registro_superheroes

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.pedrosaez.registro_superheroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var heroImage:ImageView
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btSave.setOnClickListener {
            //al pulsar save guardamos los datos introducidos en los textos
            val superHeroName = binding.etHeroName.text.toString()
            val alterEgo = binding.etRealName.text.toString()
            val bio = binding.etBio.text.toString()
            val power = binding.rbStars.rating

            //pasamos las variables como argumentos de una insatancia de hero
            val hero = SuperHero(superHeroName,alterEgo,bio,power)
            //llamamos a la función pasando hero por parámetro que creará un intent que nos llevará
            //a la detail activity
            openDetailActivity(hero)
        }

        heroImage = binding.ivHeroImage
        //al pulsar la imagen llamamos a la función que abrirá la camara  para tomar una imagen
        heroImage.setOnClickListener{
            openCamera()


        }
    }

    //función que abre la camara  para tomar una imagen que recibirá en onActivityResult
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 1000)
    }

    private fun openDetailActivity(hero: SuperHero){
            val intent = Intent(this,DetailActivity::class.java,)
            intent.putExtra(DetailActivity.SUPERHERO_KEY,hero)
            intent.putExtra(DetailActivity.BITMAP_KEY,heroImage.drawable.toBitmap())
            startActivity(intent)

        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == 1000){

            val extras = data?.extras
            val heroBitmap = extras?.getParcelable<Bitmap>("data")
            heroImage.setImageBitmap(heroBitmap)
        }
    }

}