package com.example.detail.event.presentation

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.detail.R
import com.example.detail.event.model.Event
import com.google.gson.Gson

class EventDetailActivity : AppCompatActivity() {

    private lateinit var event: Event
    private lateinit var toolbar: Toolbar
    private lateinit var ibBack: ImageButton
    private lateinit var ivTitle: ImageView
    private lateinit var tvDescription: TextView
    private lateinit var tvCost: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvStarts: TextView
    private lateinit var tvEnds: TextView
    private lateinit var tvBusinessName: TextView
    private lateinit var ibFavorite: ImageButton
    private lateinit var rvPhotos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        toolbar = findViewById(R.id.tbTitle)
        ivTitle = findViewById(R.id.ivTitle)
        ibBack = findViewById(R.id.ibBack)
        tvDescription = findViewById(R.id.tvDescription)
        tvCost = findViewById(R.id.tvCost)
        tvLocation = findViewById(R.id.tvLocation)
        tvStarts = findViewById(R.id.tvStarts)
        tvEnds = findViewById(R.id.tvEnds)
        tvBusinessName = findViewById(R.id.tvBusinessName)
        ibFavorite = findViewById(R.id.ibFavorite)
        rvPhotos = findViewById(R.id.rvPhotos)
        ibBack.setOnClickListener { super.onBackPressed() }
        ibFavorite.setOnClickListener {
            event.isFavorite = !event.isFavorite
            onFavoriteSelected?.invoke(event.id, event.isFavorite)
            setFavoriteResource(event.isFavorite)
        }
        intent.extras?.getString(ARG_NAME)?.let { eventString ->
            event = Gson().fromJson(eventString, Event::class.java)
            bindEvent()
        }
    }

    private fun bindEvent() {
        toolbar.title = event.name
        tvDescription.text = event.description
        tvCost.text = if(event.isFree) "it's free" else "$ ${event.cost}.00"
        tvLocation.text = event.location.city
        tvStarts.text = "starts: ${event.timeStart}"
        tvEnds.text = "ends: ${event.timeEnd}"
        setFavoriteResource(event.isFavorite)
        Glide.with(this).load(event.imageUrl).into(ivTitle)
        event.business?.run {
        tvBusinessName.text = name
            val photos = (0..3).map { imageRestaurant.first() }
            val photoAdapter = EventBusinessCardAdapter(photos)
            rvPhotos.layoutManager =  LinearLayoutManager(this@EventDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            rvPhotos.adapter = photoAdapter
        }
    }

    private fun setFavoriteResource(isFavorite: Boolean) {
        val res = if(isFavorite) R.drawable.ic_favorite else R.drawable.ic_not_favorite
        ibFavorite.setBackgroundResource(res)
    }

    companion object {
        const val ARG_NAME ="event"

        var onFavoriteSelected: ((String, Boolean) -> Unit)? = null

    }
}