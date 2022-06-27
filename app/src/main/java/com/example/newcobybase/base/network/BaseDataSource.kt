package com.example.newcobybase.base.network

abstract class BaseDataSource {

    protected fun parseError(
        responseMessage: String?,
        responseCode: Int,
        errorBody: String?
    ): BaseNetworkException {

        val baseNetworkException = BaseNetworkException(responseMessage, responseCode)
        errorBody?.let {
            baseNetworkException.parseFromString(it)
        }

        return baseNetworkException
    }

    protected fun parseNetworkErrorException(throwable: Throwable): NetworkErrorException {
        return NetworkErrorException(throwable.message)
    }


}