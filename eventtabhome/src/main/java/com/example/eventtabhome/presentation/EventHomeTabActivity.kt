package com.example.eventtabhome.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.eventtabhome.R
import com.google.android.material.tabs.TabLayout

class EventHomeTabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_home_tab)

        val viewPager = findViewById<ViewPager>(R.id.vpHome)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val businessFragment = BusinessFragment()
        val eventFragment = EventFragment()

        val adapter = HomeViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(eventFragment, getString(R.string.tab_title_events))
        adapter.addFragment(businessFragment, getString(R.string.tab_title_business))

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_event)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_business)
    }
}