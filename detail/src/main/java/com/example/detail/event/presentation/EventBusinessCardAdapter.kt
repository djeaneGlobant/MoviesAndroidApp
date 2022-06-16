package com.example.detail.event.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.detail.R

class EventBusinessCardAdapter(private val photos: List<String>) :
    RecyclerView.Adapter<EventBusinessCardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventBusinessCardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_detail_business_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventBusinessCardAdapter.ViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val ivBusinessImage: ImageView = view.findViewById(R.id.ivBusinessImage)

        fun bind(photo: String) {
            Glide.with(view.context).load(photo).into(ivBusinessImage)
        }
    }
}