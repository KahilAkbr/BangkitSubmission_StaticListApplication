package com.example.filmin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListFilmAdapter(private val listFilm: ArrayList<Film>): RecyclerView.Adapter<ListFilmAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object {
        const val FILM_DATA = "film_data"
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvView: TextView = itemView.findViewById(R.id.tv_item_view)
        val tvRating: TextView = itemView.findViewById(R.id.tv_item_rating)
        val tvDuration: TextView = itemView.findViewById(R.id.tv_item_duration)
        val tvProducer: TextView = itemView.findViewById(R.id.tv_item_producer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listFilm.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo, views, rating, duration, producer) = listFilm[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvView.text = views
        holder.tvRating.text = rating
        holder.tvDuration.text = duration
        holder.tvProducer.text = producer

        holder.itemView.setOnClickListener{
            val moveDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            moveDetail.putExtra(FILM_DATA, listFilm[holder.adapterPosition])
            holder.itemView.context.startActivity(moveDetail)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Film)
    }
}