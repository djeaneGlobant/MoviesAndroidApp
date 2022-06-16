package com.example.eventlist.presentation.businesslist

import android.graphics.drawable.AnimatedVectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.eventlist.R
import com.example.eventlist.domain.model.Business
import com.squareup.picasso.Picasso

internal class BusinessListAdapter(
    private val data: List<Business>,
    private val onClickFavorite: (String, Boolean) -> Unit,
    private val onClickBusiness: (Business, Int) -> Unit,
) :
    RecyclerView.Adapter<BusinessListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BusinessListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.business_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusinessListAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun update(id: String, isFavorite: Boolean) {
        val position = data.indexOfFirst { it.id == id}
        data[position].isFavorite = isFavorite
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val ivBusiness: ImageView = view.findViewById(R.id.ivBusinessImage)
        private val tvName: TextView = view.findViewById(R.id.tvBusinessName)
        private val tvDescription: TextView = view.findViewById(R.id.tvBusinessDescription)
        private val rbRating: RatingBar = view.findViewById(R.id.rbRating)
        private val ibFavorite: ImageButton = view.findViewById(R.id.ibBusinessFavorite)

        fun bind(business: Business) {
            tvName.text = business.name
            business.imageRestaurant?.firstOrNull()?.run {
                Picasso.get().load(this).into(ivBusiness)
            }
            business.reviews?.firstOrNull()?.run {
                tvDescription.text = this.comment
            }
            rbRating.rating = business.rating?.toFloat() ?: 0f
            view.setOnClickListener { onClickBusiness.invoke(business, this.adapterPosition) }
            animate(ibFavorite)
            ibFavorite.setBackgroundResource(getResource(business.isFavorite))
            ibFavorite.setOnClickListener {
                toggleFavorite(business)
                animate(it)
            }
        }

        private fun toggleFavorite(business: Business) {
            business.isFavorite = !business.isFavorite
            val favoriteDrawable = getResource(business.isFavorite)
            ibFavorite.setBackgroundResource(favoriteDrawable)
            onClickFavorite(business.id, business.isFavorite)
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

        private fun getResource(isFavorite: Boolean): Int {
            return if (isFavorite) R.drawable.favorite else R.drawable.not_favorite
        }
    }
}