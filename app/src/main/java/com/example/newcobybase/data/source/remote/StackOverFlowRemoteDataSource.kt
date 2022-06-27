package com.example.newcobybase.data.source.remote

import com.example.newcobybase.base.network.BaseRemoteDataSource
import com.example.newcobybase.base.network.NetworkResult
import com.example.newcobybase.data.api.StackOverFlowApi
import com.example.newcobybase.data.apiModel.QuestionApiModel
import javax.inject.Inject

class StackOverFlowRemoteDataSource @Inject constructor(private val stackOverFlowApi: StackOverFlowApi) :
    BaseRemoteDataSource() {

    suspend fun getQuestion(page: Int, pageSize: Int): NetworkResult<QuestionApiModel> {
        val parameter = mutableMapOf<String, String>()
        parameter["page"] = page.toString()
        parameter["pageSize"] = pageSize.toString()
        parameter["site"] = "stackoverflow"
        return callApi { stackOverFlowApi.getQuestions(parameters = parameter) }
    }
}