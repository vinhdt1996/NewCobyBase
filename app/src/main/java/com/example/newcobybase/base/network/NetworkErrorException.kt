package com.example.newcobybase.base.network

open class NetworkErrorException(val responseMessage: String? = null) : Exception() {
}