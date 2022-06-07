package com.example.eventlist.util

sealed class DataState<out T> {
    data class Success<T>(val data: T?): DataState<T>()
    data class Failure<T>(val exception: Exception): DataState<T>()
}