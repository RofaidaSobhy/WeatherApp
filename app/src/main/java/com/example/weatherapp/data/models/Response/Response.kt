package com.example.weatherapp.data.models.Response

sealed class Response {
    data object Loading : Response()
    data class Success<T>(val data: T?) : Response()
    data class Failure(val error: Throwable) : Response()
}