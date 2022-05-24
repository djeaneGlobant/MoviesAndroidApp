package com.example.eventtabhome

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.eventlist.presentation.eventlist.EventListFragment
import com.example.eventtabhome.databinding.ActivityEventHomeTabBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class EventHomeTabActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventHomeTabBinding
    private lateinit var etQuery: EditText
    private lateinit var btnSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventHomeTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        etQuery = binding.etQuery
        btnSearch = binding.btnSearch

        val navController = findNavController(R.id.nav_host)


        navView.setupWithNavController(navController)


    }

    override fun onResume() {
        super.onResume()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host)
        val fragment  = navHostFragment?.childFragmentManager?.fragments?.get(0) as EventListFragment

        btnSearch.setOnClickListener {
            val query = etQuery.text
            fragment.searchEvents(query.toString())
        }

    }
}