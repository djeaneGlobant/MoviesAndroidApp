package com.example.eventlist.presentation.util


internal sealed class UIState {
    class Success(val isEmpty: Boolean) : UIState()
    object Error : UIState()
    object Loading : UIState()
}