package com.example.eventlist.presentation.eventlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventlist.data.network.DataState
import com.example.eventlist.domain.model.Event
import com.example.eventlist.domain.usecase.GetEventsUseCase
import com.example.eventlist.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class EventListViewModel @Inject constructor(
    private val getEventsUseCaseImpl: GetEventsUseCase,
    private val toggleFavoriteUseCaseImpl: ToggleFavoriteUseCase
) : ViewModel() {
    private val _events: MutableLiveData<List<Event>> = MutableLiveData(emptyList())
    private val _uiState: MutableLiveData<UIState> = MutableLiveData()
    val events: LiveData<List<Event>>
        get() = _events
    val uiState: LiveData<UIState>
        get() = _uiState

    fun setStateEvent(stateEvent: EventListStateEvent) {
        with(stateEvent) {
            when (this) {
                is EventListStateEvent.SearchEvents -> {
                    getMovies(query)
                }
                is EventListStateEvent.ToggleFavorite -> {
                    toggleFavorite(id, isFavorite)
                }
            }
        }

    }

    private fun getMovies(query: String? = null) {
        _uiState.value = UIState.Loading
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                getEventsUseCaseImpl.invoke(query)
            }
            when (response) {
                is DataState.Success -> {
                    _events.value = response.data!!
                    _uiState.value = UIState.Success
                }
                is DataState.Failure -> {
                    response.exception.printStackTrace()
                    _uiState.value = UIState.Error
                }
            }
        }
    }

    private fun toggleFavorite(id: String, isFavorite: Boolean) {
        viewModelScope.launch {
                toggleFavoriteUseCaseImpl.invoke(id, isFavorite)
        }
    }

}

internal sealed class EventListStateEvent {
    class SearchEvents(val query: String? = null) : EventListStateEvent()
    class ToggleFavorite(val id: String, val isFavorite: Boolean) : EventListStateEvent()
}

internal sealed class UIState {
    object Success : UIState()
    object Error : UIState()
    object Loading : UIState()
}