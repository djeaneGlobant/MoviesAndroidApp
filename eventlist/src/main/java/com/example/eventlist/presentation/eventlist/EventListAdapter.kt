package com.example.eventlist.presentation.eventlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventlist.R
import com.example.eventlist.domain.model.Event
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

internal class EventListAdapter(
    private val data: List<Event>,
    private val onClickMovie: (Event) -> Unit,
    private val onClickFavorite: (String, Boolean) -> Unit
) : RecyclerView.Adapter<EventListAdapter.EventsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false)
        return EventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class EventsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val ivMovieImage = view.findViewById<ImageView>(R.id.ivMovieImage)
        private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        private val tvCity = view.findViewById<TextView>(R.id.tvCity)
        private val tvYearOfRelease = view.findViewById<TextView>(R.id.tvYearOfRelease)
        private val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        private val ibFavorite = view.findViewById<ImageButton>(R.id.ibFavorite)

        fun bind(event: Event) {
            with(event) {
                Picasso.get().load(imageUrl).into(ivMovieImage)
                tvTitle.text = name
                tvCity.text = "(${location.city})"
                tvDescription.text = description
                tvYearOfRelease.text = toFormat(event.timeStart)
                ibFavorite.setBackgroundResource(getResource(isFavorite))
                view.setOnClickListener { onClickMovie(event) }
                ibFavorite.setOnClickListener {
                    toggleFavorite(event)
                }
            }
        }

        private fun toggleFavorite(event: Event) {
            event.isFavorite = !event.isFavorite
            val favoriteDrawable = getResource(event.isFavorite)
            ibFavorite.setBackgroundResource(favoriteDrawable)
            onClickFavorite(event.id, event.isFavorite)
        }

        private fun getResource(isFavorite: Boolean): Int {
            return if(isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        }

        private fun toFormat(dateString: String): String {
            val datePath = dateString.substring(0, 19)
            val tzPath = dateString.substring(20).replace(":", "")
            val dateStringClean = "$datePath-$tzPath"
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", view.context.resources.configuration.locale)
            val date = formatter.parse(dateStringClean)!!
            formatter.applyPattern("E MMM d, yyyy")
            return formatter.format(date)
        }
    }

}