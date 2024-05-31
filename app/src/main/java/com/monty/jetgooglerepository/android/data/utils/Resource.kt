package com.monty.jetgooglerepository.android.data.utils

/**
 * This is a wrapper class for network requests response
 *
 * @param data :T Generic data type
 * @param message: String, will be returned in case of error
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}