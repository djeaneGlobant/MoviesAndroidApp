package com.example.eventtabhome.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eventlist.presentation.eventlist.EventListFragment
import com.example.eventtabhome.R
import com.example.eventtabhome.widgets.SearchBar

class EventFragment : Fragment() {
    private lateinit var searchBar: SearchBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val eventListFragment = EventListFragment.newInstance (onLocationsLoaded = ::onLocationsLoaded)
        parentFragmentManager.beginTransaction()
            .replace(R.id.flEvents, eventListFragment)
            .commit()

        val view = inflater.inflate(R.layout.fragment_event, container, false)
        searchBar = view.findViewById(R.id.sbEvent)

        searchBar.setOnSelectedOption { location ->
            eventListFragment.searchEvents(location)
        }
        return view
    }

    private fun onLocationsLoaded(locations: List<String>) {
        searchBar.setOptions(locations)
    }
}