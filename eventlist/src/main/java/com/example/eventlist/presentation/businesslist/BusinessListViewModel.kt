package com.example.eventlist.presentation.businesslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventlist.data.network.DataState
import com.example.eventlist.domain.model.Business
import com.example.eventlist.domain.usecase.GetBusinessByFoodAndLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BusinessListViewModel @Inject constructor(
    private val getBusinessUseCase: GetBusinessByFoodAndLocationUseCase
) : ViewModel() {
    private val _business: MutableLiveData<List<Business>> = MutableLiveData(emptyList())

    val business: LiveData<List<Business>> = _business

    fun loadBusiness() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getBusinessUseCase.invoke("restaurants", "New York City")
            }
            when (result) {
                is DataState.Success -> {
                    result.data?.run { _business.value = this}
                }
                is DataState.Failure -> {
                    println("*_*error: ${result.exception}")
                }
            }
        }
    }
}