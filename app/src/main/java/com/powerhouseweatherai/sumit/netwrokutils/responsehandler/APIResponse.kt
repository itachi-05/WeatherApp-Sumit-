package com.powerhouseweatherai.sumit.netwrokutils.responsehandler


sealed class APIResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val errorBody: String? = null
) {

    class Success<T>(data: T) : APIResponse<T>(data)
    class Error<T>(message: String?, data: T? = null,  errorBody: String? = null) : APIResponse<T>(data, message, errorBody)
    class Loading<T> : APIResponse<T>()

}