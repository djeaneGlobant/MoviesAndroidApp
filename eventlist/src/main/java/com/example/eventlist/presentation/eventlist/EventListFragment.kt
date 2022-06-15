package com.example.eventlist.presentation.eventlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.viewmodel.ViewModelFactory
import com.example.eventlist.R
import com.example.eventlist.domain.model.Event
import com.example.eventlist.presentation.util.UIState
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class EventListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val model: EventListViewModel by viewModels { viewModelFactory }
    private lateinit var rvFlEvents: RecyclerView
    private lateinit var pbLoading: ProgressBar
    private lateinit var tvEmptyResults: TextView
    private var currentPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event_list, container, false)
        pbLoading = view.findViewById(R.id.pbLoading)
        rvFlEvents = view.findViewById(R.id.rvFlEvents)
        tvEmptyResults = view.findViewById(R.id.tvEmptyResults)
        rvFlEvents.layoutManager = LinearLayoutManager(context)
        model.setStateEvent(EventListStateEvent.SearchEvents())
        model.getLocations(onLocationsLoaded)
        model.events.observe(viewLifecycleOwner) {
            setMovies(it)
        }
        model.uiState.observe(viewLifecycleOwner) {
            pbLoading.visibility = View.GONE
            rvFlEvents.visibility = View.GONE
            tvEmptyResults.visibility =  View.GONE
            when(it) {
                is UIState.Success -> {
                    if(it.isEmpty) {
                        tvEmptyResults.visibility =  View.VISIBLE
                    } else {
                        rvFlEvents.visibility = View.VISIBLE
                    }
                }
                is UIState.Loading -> {
                    pbLoading.visibility = View.VISIBLE
                }
                is UIState.Error -> {
                    tvEmptyResults.visibility =  View.VISIBLE
                    val errorMessage = view.resources.getString(R.string.common_ui_error)
                    Snackbar.make(view.rootView, errorMessage, BaseTransientBottomBar.LENGTH_LONG).show()
                }
            }
        }
        return view
    }

    private fun setMovies(events: List<Event>) {
        rvFlEvents.adapter = EventListAdapter(events,
            onClickEvent = { event, position ->
                currentPosition = position
                onClickEvent?.invoke(event)
            }, onClickFavorite = { id, isFavorite ->
                model.setStateEvent(EventListStateEvent.ToggleFavorite(id, isFavorite))
            })
    }

    fun searchEvents(query: String) {
        model.setStateEvent(EventListStateEvent.SearchEvents(query))
    }

    fun toggleFavorite(id: String, isFavorite: Boolean) {
        model.setStateEvent(EventListStateEvent.ToggleFavorite(id, isFavorite))
        (rvFlEvents.adapter as EventListAdapter).update(id, isFavorite)
        rvFlEvents.adapter?.notifyItemChanged(currentPosition!!)
    }

    companion object {
        private var onClickEvent: ((Event) -> Unit)? = null
        private var onLocationsLoaded: ((List<String>) -> Unit)? = null


        @JvmStatic
        fun newInstance(
            onClickEvent: ((Event) -> Unit)? = null,
            onLocationsLoaded: ((List<String>) -> Unit)? = null
        ) =
            EventListFragment().apply {
                Companion.onClickEvent = onClickEvent
                Companion.onLocationsLoaded = onLocationsLoaded
            }
    }
}