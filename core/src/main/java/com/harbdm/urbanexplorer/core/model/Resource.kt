package com.harbdm.urbanexplorer.core.model


sealed class Resource<T>(
    val data: T? = null,
    val throwable: Throwable? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(throwable: Throwable?, message: String? = null, data: T? = null) : Resource<T>(data,throwable,message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}