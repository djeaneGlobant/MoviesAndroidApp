package com.example.movielist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.domain.model.Movie
import com.squareup.picasso.Picasso

internal class MoviesAdapter(
    private val data: List<Movie>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class MoviesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val ivMovieImage = view.findViewById<ImageView>(R.id.ivMovieImage)
        private val tvMovieTitle = view.findViewById<TextView>(R.id.tvMovieTitle)

        fun bind(movie: Movie) {
            with(movie) {
                Picasso.get().load(postPath).into(ivMovieImage)
                tvMovieTitle.text = originalTitle
                view.setOnClickListener { onClick(id) }

            }
        }
    }

}