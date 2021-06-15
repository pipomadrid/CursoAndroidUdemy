package com.pedrosaez.earthquakemonitor.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pedrosaez.earthquakemonitor.R
import com.pedrosaez.earthquakemonitor.databinding.ActivityDetailsBinding
import java.text.SimpleDateFormat
import java.util.*
import com.pedrosaez.earthquakemonitor.Earthquake


class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EQ_KEY = "earthquake"

        private const val TIME_FORMAT = "dd/MMM/yyyy HH:mm:ss"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val earthquake= intent?.extras?.getParcelable<Earthquake>(EQ_KEY)!!
        binding.eqMagnitudeText.text = getString(R.string.magnitude_format, earthquake.magnitude)
        binding.longitudeText.text = earthquake.longitude.toString()
        binding.latitudeText.text = earthquake.latitude.toString()
        binding.eqPlaceText.text = earthquake.place
        setupTime(binding, earthquake)
    }

    private fun setupTime(binding: ActivityDetailsBinding, earthquake: Earthquake) {

        val dateFormat = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        val date = Date(earthquake.time)
        binding.eqTimeText.text = dateFormat.format(date)
    }


}