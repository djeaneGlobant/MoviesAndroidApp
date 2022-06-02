package com.example.eventlist.presentation.businesslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventlist.R
import com.example.eventlist.domain.model.Business
import com.squareup.picasso.Picasso

class BusinessListAdapter(private val data: List<Business>) :
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivBusiness: ImageView = view.findViewById(R.id.ivBusinessImage)
        private val tvName: TextView = view.findViewById(R.id.tvBusinessName)
        private val tvDescription: TextView = view.findViewById(R.id.tvBusinessDescription)

        fun bind(business: Business) {
            tvName.text = business.name
            business.imageRestaurant?.firstOrNull()?.run {
                Picasso.get().load(this).into(ivBusiness)
            }
            business.reviews?.firstOrNull()?.run {
                tvDescription.text = this.comment
            }
        }
    }
}