package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.UseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
    private val get: UseCases
): ViewModel() {

    fun getBusinessByFoodAndLocation() {
        viewModelScope.launch {
            // TODO - replace parameters with real data below
            get.businessUseCase("pizza", "san francisco")?.let {
                if (it.search.business?.isNotEmpty() != null) {

                }
            }
        }
    }

    fun getEvents() {
        viewModelScope.launch {
            get.eventUseCase()?.let {
                if(it.eventSearch.events?.isNotEmpty() != null) {

                }
            }
        }
    }
}