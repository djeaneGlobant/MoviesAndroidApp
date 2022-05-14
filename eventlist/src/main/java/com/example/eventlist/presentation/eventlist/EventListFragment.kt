package com.example.eventlist.presentation.eventlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.viewmodel.ViewModelFactory
import com.example.eventlist.domain.model.Event
import com.example.eventlist.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class EventListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val model: EventListViewModel by viewModels { viewModelFactory }
    lateinit var rvFlEvents: RecyclerView
    private lateinit var pbLoading: ProgressBar

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
        rvFlEvents.layoutManager = LinearLayoutManager(context)
        model.setStateEvent(EventListStateEvent.SearchEvents())
        model.events.observe(viewLifecycleOwner) {
            setMovies(it)
        }
        model.uiState.observe(viewLifecycleOwner) {
            when(it) {
                is UIState.Success -> {
                    rvFlEvents.visibility = View.VISIBLE
                    pbLoading.visibility = View.GONE
                }
                is UIState.Loading -> {
                    rvFlEvents.visibility = View.GONE
                    pbLoading.visibility = View.VISIBLE
                }
                is UIState.Error -> {
                    rvFlEvents.visibility = View.VISIBLE
                    pbLoading.visibility = View.GONE
                    val errorMessage = view.resources.getString(R.string.common_ui_error)
                    Snackbar.make(view.rootView, errorMessage, BaseTransientBottomBar.LENGTH_LONG).show()
                }
            }
        }
        return view
    }

    private fun setMovies(events: List<Event>) {
        rvFlEvents.adapter = EventListAdapter(events,
            onClickMovie = { movie ->
                onClickEvent?.invoke(movie)
            }, onClickFavorite = { id, isFavorite ->
                model.setStateEvent(EventListStateEvent.ToggleFavorite(id, isFavorite))
                onClickFavorite?.invoke(id, isFavorite)
            })
    }

    fun searchEvents(query: String) {
        model.setStateEvent(EventListStateEvent.SearchEvents(query))
    }

    companion object {
        private var onClickEvent: ((Event) -> Unit)? = null
        private var onClickFavorite: ((String, Boolean) -> Unit)? = null


        @JvmStatic
        fun newInstance(
            onClickEvent: ((Event) -> Unit)? = null,
            onClickFavorite: ((String, Boolean) -> Unit)? = null
        ) =
            EventListFragment().apply {
                Companion.onClickEvent = onClickEvent
                Companion.onClickFavorite = onClickFavorite
            }
    }
}