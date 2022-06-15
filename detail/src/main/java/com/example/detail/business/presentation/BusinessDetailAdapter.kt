package com.example.detail.business.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.detail.R
import com.example.detail.business.model.Business

internal class BusinessDetailAdapter(
    private val data: List<Business.Review>
) : RecyclerView.Adapter<BusinessDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.business_review_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size


    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
        private val tvUsername: TextView = view.findViewById(R.id.tvUsername)
        private val tvComment: TextView = view.findViewById(R.id.tvComment)
        private val rbRating: RatingBar = view.findViewById(R.id.rbRating)


        fun bind(review: Business.Review) {
            review.user?.imageUrl?.run {
                Glide.with(view).load(this).into(ivAvatar)
            }
            tvUsername.text = review.user?.name
            tvComment.text = review.comment
            rbRating.rating = review.rating?.toFloat() ?: 0f
        }
    }
}