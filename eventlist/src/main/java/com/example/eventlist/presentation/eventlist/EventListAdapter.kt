package com.example.eventlist.presentation.eventlist

import android.graphics.drawable.AnimatedVectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.eventlist.R
import com.example.eventlist.domain.model.Event
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

internal class EventListAdapter(
    private val data: List<Event>,
    private val onClickEvent: (Event, Int) -> Unit,
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

    fun update(id: String, isFavorite: Boolean) {
        val position = data.indexOfFirst { it.id == id}
        data[position].isFavorite = isFavorite
    }

    inner class EventsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val ivMovieImage = view.findViewById<ImageView>(R.id.ivMovieImage)
        private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        private val tvYearOfRelease = view.findViewById<TextView>(R.id.tvYearOfRelease)
        private val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        private val ibFavorite = view.findViewById<ImageButton>(R.id.ibFavorite)

        fun bind(event: Event) {
            with(event) {
                Picasso.get().load(imageUrl).error(R.drawable.error_loaded_image).into(ivMovieImage)
                tvTitle.text = name
                tvDescription.text = description
                tvYearOfRelease.text = toFormat(event.timeStart)
                ibFavorite.setBackgroundResource(getResource(isFavorite))
                animate(ibFavorite)
                view.setOnClickListener { onClickEvent(event, adapterPosition) }
                ibFavorite.setOnClickListener {
                    toggleFavorite(event)
                    animate(it)
                }
            }
        }

        private fun animate(_view: View) {
            when (val drawable = _view.background) {
                is AnimatedVectorDrawableCompat -> {
                    drawable.start()
                }
                is AnimatedVectorDrawable -> {
                    drawable.start()
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
            return if (isFavorite) R.drawable.favorite else R.drawable.not_favorite
        }

        @Suppress("DEPRECATION")
        private fun toFormat(dateString: String): String {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", view.context.resources.configuration.locale)
            val date = formatter.parse(dateString)!!
            formatter.applyPattern("E MMM d, yyyy")
            return formatter.format(date)
        }
    }

}