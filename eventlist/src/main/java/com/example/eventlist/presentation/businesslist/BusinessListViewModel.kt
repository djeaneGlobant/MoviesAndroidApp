package com.example.eventlist.presentation.businesslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventlist.domain.model.Business
import com.example.eventlist.domain.usecase.business.GetBusiness
import com.example.eventlist.domain.usecase.business.GetCategories
import com.example.eventlist.domain.usecase.business.GetLocations
import com.example.eventlist.domain.usecase.business.ToggleFavorite
import com.example.eventlist.presentation.util.UIState
import com.example.eventlist.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class BusinessListViewModel @Inject constructor(
    private val getBusiness: GetBusiness,
    private val getLocations: GetLocations,
    private val getCategories: GetCategories,
    private val toggleFavorite: ToggleFavorite
) : ViewModel() {
    private val _business: MutableLiveData<List<Business>> = MutableLiveData(emptyList())
    private val _uiState: MutableLiveData<UIState> = MutableLiveData(UIState.Loading)

    val business: LiveData<List<Business>>
        get() = _business
    val uiState: LiveData<UIState>
        get() = _uiState


    private fun loadBusiness(term: String, location: String) {
        _uiState.value = UIState.Loading
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                getBusiness.invoke(term, location)
            }
            when (response) {
                is DataState.Success -> {
                    response.data?.run { _business.value = this }
                    _uiState.value = UIState.Success(response.data.isNullOrEmpty())
                }
                is DataState.Failure -> {
                    _uiState.value = UIState.Error
                    response.exception.printStackTrace()
                }
            }
        }
    }

    fun setStateEvent(event: BusinessListStateEvent) {
        when (event) {
            is BusinessListStateEvent.LoadBusiness -> {
                loadBusiness(event.term, event.location)
            }
            is BusinessListStateEvent.LoadCategories -> {
                loadCategories(event.onLoad)
            }
            is BusinessListStateEvent.LoadLocations -> {
                loadLocations(event.onLoad)
            }
            is BusinessListStateEvent.ToggleFavorite -> {
                toggleFavorite(event.id, event.isFavorite)
            }
        }
    }

    private fun loadLocations(onLoad: (List<String>) -> Unit) {
        viewModelScope.launch {
            when (val response = withContext(Dispatchers.IO) { getLocations.invoke() }) {
                is DataState.Success -> {
                    val result = response.data ?: emptyList()
                    onLoad(result)
                }
                is DataState.Failure -> {
                    response.exception.printStackTrace()
                }
            }
        }
    }

    private fun loadCategories(onLoad: (List<String>) -> Unit) {
        viewModelScope.launch {
            when (val response = withContext(Dispatchers.IO) { getCategories.invoke() }) {
                is DataState.Success -> {
                    val result = response.data ?: emptyList()
                    onLoad(result)
                }
                is DataState.Failure -> {
                    response.exception.printStackTrace()
                }
            }
        }
    }

    private fun toggleFavorite(id: String, isFavorite: Boolean) {
        viewModelScope.launch {
            toggleFavorite.invoke(id, isFavorite)
        }
    }
}


internal sealed class BusinessListStateEvent {
    class LoadBusiness(val term: String, val location: String) : BusinessListStateEvent()
    class LoadCategories(val onLoad: (List<String>) -> Unit) : BusinessListStateEvent()
    class LoadLocations(val onLoad: (List<String>) -> Unit) : BusinessListStateEvent()
    class ToggleFavorite(val id: String, val isFavorite: Boolean) : BusinessListStateEvent()
}