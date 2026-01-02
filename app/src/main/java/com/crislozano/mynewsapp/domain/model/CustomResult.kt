package com.crislozano.mynewsapp.domain.model

sealed class CustomResult<T>(
    val errorMessage: String? = null,
    val data: T? = null
) {
    class Success<T>(data: T) : CustomResult<T>(data = data)
    class Error<T>(errorMessage: String) : CustomResult<T>(errorMessage = errorMessage)

}