package com.example.newcobybase.data.repository

import com.example.newcobybase.base.network.NetworkResult
import com.example.newcobybase.data.source.remote.JsonPlaceHolderRemoteDataSource
import com.example.newcobybase.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JsonPlaceHolderRepository @Inject constructor(
    private val jsonPlaceHolderRemoteService: JsonPlaceHolderRemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllPosts() = withContext(dispatcher) {
        when (val result = jsonPlaceHolderRemoteService.getAllPosts()) {
            is NetworkResult.Success -> {
                result.data.map { it.toPost() }
            }
            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }

}