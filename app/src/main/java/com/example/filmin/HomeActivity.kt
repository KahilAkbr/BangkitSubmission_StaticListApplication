package com.example.filmin

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvFilms: RecyclerView
    private val list = ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnMoveAboutMe: ImageButton = findViewById(R.id.about_page)
        btnMoveAboutMe.setOnClickListener(this)

        rvFilms = findViewById(R.id.rv_films)
        rvFilms.setHasFixedSize(true)

        list.addAll(getListFilms())
        showRecyclerList()

    }

    private fun showRecyclerList() {
        rvFilms.layoutManager = LinearLayoutManager(this)
        val listFilmAdapter = ListFilmAdapter(list)
        rvFilms.adapter = listFilmAdapter

        listFilmAdapter.setOnItemClickCallback(object : ListFilmAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Film) {
                showSelectedFilm(data)
            }
        })

    }

    private fun getListFilms(): ArrayList<Film> {
        val filmName = resources.getStringArray(R.array.data_name)
        val photo = resources.obtainTypedArray(R.array.data_photo)
        val views = resources.getStringArray(R.array.data_views)
        val rating = resources.getStringArray(R.array.data_rating)
        val duration = resources.getStringArray(R.array.data_duration)
        val producer = resources.getStringArray(R.array.data_producer)
        val synopsis = resources.getStringArray(R.array.data_synopsis)

        val listFilm = ArrayList<Film>()
        for(i in filmName.indices){
            val film = Film(filmName[i], photo.getResourceId(i, -1), views[i], rating[i], duration[i], producer[i], synopsis[i])
            listFilm.add(film)
        }
        return listFilm
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.about_page ->{
                val moveAbout = Intent(this@HomeActivity, AboutMeActivity::class.java)
                startActivity(moveAbout)

            }
        }
    }

    private fun showSelectedFilm(film: Film) {
        Toast.makeText(this, "Kamu memilih " + film.name, Toast.LENGTH_SHORT).show()
    }
}

