package com.example.newcobybase.data.repository

import com.example.newcobybase.base.network.NetworkResult
import com.example.newcobybase.data.source.remote.StackOverFlowRemoteDataSource
import com.example.newcobybase.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StackOverFlowRepository @Inject constructor(
    private val stackOverFlowRemoteService: StackOverFlowRemoteDataSource,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) {
    suspend fun getQuestion(page: Int, pageSize: Int) = withContext(dispatcher) {
        when (val result = stackOverFlowRemoteService.getQuestion(page, pageSize)) {
            is NetworkResult.Success -> {
                result.data
            }
            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }
}