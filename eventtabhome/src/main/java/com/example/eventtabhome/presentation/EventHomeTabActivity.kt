package com.example.eventtabhome.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.eventtabhome.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class EventHomeTabActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityEventHomeTabBinding
//    private lateinit var etQuery: EditText
//    private lateinit var btnSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityEventHomeTabBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_event_home_tab)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        val navView: BottomNavigationView = binding.navView
//        etQuery = binding.etQuery
//        btnSearch = binding.btnSearch

        val navController = findNavController(R.id.nav_host)


        navView.setupWithNavController(navController)


    }

    override fun onResume() {
        super.onResume()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host)
//        val fragment  = navHostFragment?.childFragmentManager?.fragments?.get(0) as EventListFragment

//        btnSearch.setOnClickListener {
//            val query = etQuery.text
//            fragment.searchEvents(query.toString())
//        }

    }
}