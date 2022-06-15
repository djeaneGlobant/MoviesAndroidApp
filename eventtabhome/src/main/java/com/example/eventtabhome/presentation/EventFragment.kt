package com.example.eventtabhome.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.detail.event.presentation.EventDetailActivity
import com.example.eventlist.domain.model.Event
import com.example.eventlist.presentation.eventlist.EventListFragment
import com.example.eventtabhome.R
import com.example.eventtabhome.widgets.SearchBar
import com.google.gson.Gson

class EventFragment : Fragment() {
    private lateinit var searchBar: SearchBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = EventListFragment.newInstance(
            onLocationsLoaded = ::onLocationsLoaded,
            onClickEvent = ::onClickEvent,
        )
        EventDetailActivity.onFavoriteSelected = { id, isFavorite ->
            fragment.toggleFavorite(id, isFavorite)
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.flEvents, fragment)
            .commit()

        val view = inflater.inflate(R.layout.fragment_event, container, false)
        searchBar = view.findViewById(R.id.sbEvent)

        searchBar.setOnSelectedOption { location ->
            fragment.searchEvents(location)
        }
        return view
    }

    private fun onLocationsLoaded(locations: List<String>) {
        searchBar.setOptions(locations)
    }

    private fun onClickEvent(event: Event) {
        val eventString = Gson().toJson(event)
        val intent = Intent(requireActivity(), EventDetailActivity::class.java)
        intent.putExtra(EventDetailActivity.ARG_NAME, eventString)
        requireActivity().startActivity(intent)
    }
}