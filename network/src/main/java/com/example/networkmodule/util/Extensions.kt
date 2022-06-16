package com.example.networkmodule.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.mapToObject(any: Any?): T =
    fromJson(toJson(any), object : TypeToken<T>() {}.type)