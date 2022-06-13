package com.example.detail.business.presentation

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.detail.R
import com.example.detail.business.model.Business
import com.google.gson.Gson

class BusinessDetailActivity : AppCompatActivity() {

    private lateinit var business: Business

    private lateinit var toolbar: Toolbar
    private lateinit var ivTitle: ImageView
    private lateinit var rvReviews: RecyclerView
    private lateinit var ibFavorite: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_detail)
        toolbar = findViewById(R.id.tbTitle)
        ivTitle = findViewById(R.id.ivTitle)
        rvReviews = findViewById(R.id.rvReviews)
        ibFavorite = findViewById(R.id.ibFavorite)
        ibFavorite.setOnClickListener {
            business.isFavorite = !business.isFavorite
            onFavoriteSelected?.invoke(business.id, business.isFavorite)
            setFavoriteResource(business.isFavorite)
        }
        val ibBack = findViewById<ImageButton>(R.id.ibBack)
        ibBack.setOnClickListener { super.onBackPressed() }
        intent.extras?.getString(ARG_NAME)?.run {
            business = Gson().fromJson(this, Business::class.java)
        }
    }

    override fun onStart() {
        super.onStart()
        toolbar.title = business.name
//        business.reviews?.firstOrNull()?.run {
//            tvDescription.text = this.comment
//        }
        business.imageRestaurant?.firstOrNull()?.run {
            Glide.with(this@BusinessDetailActivity)
                .load(this)
                .into(ivTitle)
        }
        business.reviews?.run {
            val reviewsAdapter = BusinessDetailAdapter(this)
            val layoutManager = LinearLayoutManager(this@BusinessDetailActivity)
            layoutManager.isAutoMeasureEnabled = true
            rvReviews.layoutManager = layoutManager
            rvReviews.isNestedScrollingEnabled = false
            rvReviews.adapter = reviewsAdapter
        }
        setFavoriteResource(business.isFavorite)
    }

    private fun setFavoriteResource(isFavorite: Boolean) {
        val res = if(isFavorite) R.drawable.ic_favorite else R.drawable.ic_not_favorite
        ibFavorite.setBackgroundResource(res)
    }

    companion object {
        const val ARG_NAME = "business"

        var onFavoriteSelected: ((String, Boolean) -> Unit)? = null

    }
}