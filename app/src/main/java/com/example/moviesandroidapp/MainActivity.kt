package com.example.moviesandroidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eventlist.presentation.eventlist.EventListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainContainer, EventListFragment())
            .commit()
    }
}