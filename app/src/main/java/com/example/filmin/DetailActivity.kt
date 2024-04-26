package com.example.filmin

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val FILM_DATA = "film_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnToHome: ImageButton = findViewById(R.id.back_button)
        btnToHome.setOnClickListener(this)

        val btnShare: ImageButton = findViewById(R.id.action_share)
        btnShare.setOnClickListener(this)

        val dataFilm = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Film>(FILM_DATA, Film::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Film>(FILM_DATA)
        }
        if(dataFilm != null){
            val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
            val tvName: TextView = findViewById(R.id.tv_item_name)
            val tvView: TextView = findViewById(R.id.tv_item_view)
            val tvRating: TextView = findViewById(R.id.tv_item_rating)
            val tvDuration: TextView = findViewById(R.id.tv_item_duration)
            val tvProducer: TextView = findViewById(R.id.tv_item_producer)
            val tvSynopsis: TextView = findViewById(R.id.tv_item_synopsis)

            imgPhoto.setImageResource(dataFilm.photo)
            tvName.text = dataFilm.name
            tvView.text = dataFilm.views
            tvRating.text = dataFilm.rating
            tvDuration.text = dataFilm.rating
            tvProducer.text = dataFilm.producer
            tvSynopsis.text = dataFilm.synopsis
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.back_button->{
                onBackPressed()
            }
            R.id.action_share->{
                val dataFilm = if (Build.VERSION.SDK_INT >= 33){
                    intent.getParcelableExtra<Film>(FILM_DATA, Film::class.java)
                }else{
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra<Film>(FILM_DATA)
                }
                if(dataFilm != null){
                    val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
                    val tvName: TextView = findViewById(R.id.tv_item_name)
                    val tvView: TextView = findViewById(R.id.tv_item_view)
                    val tvRating: TextView = findViewById(R.id.tv_item_rating)
                    val tvDuration: TextView = findViewById(R.id.tv_item_duration)
                    val tvProducer: TextView = findViewById(R.id.tv_item_producer)
                    val tvSynopsis: TextView = findViewById(R.id.tv_item_synopsis)

                    imgPhoto.setImageResource(dataFilm.photo)
                    tvName.text = dataFilm.name
                    tvView.text = dataFilm.views
                    tvRating.text = dataFilm.rating
                    tvDuration.text = dataFilm.rating
                    tvProducer.text = dataFilm.producer
                    tvSynopsis.text = dataFilm.synopsis

                    val intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT, dataFilm.name + "\n" + dataFilm.synopsis)
                    intent.type = "text/plain"

                    startActivity(Intent.createChooser(intent, "Share to"))
                }
            }
        }
    }
}