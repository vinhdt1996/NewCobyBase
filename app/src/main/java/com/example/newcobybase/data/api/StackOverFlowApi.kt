package com.example.newcobybase.data.api

import com.example.newcobybase.data.apiModel.QuestionApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface StackOverFlowApi {

    @GET("{versionAPI}/questions")
    suspend fun getQuestions(
        @Path("versionAPI") versionAPI: String = "2.2",
        @QueryMap parameters: Map<String, String> = mapOf()
    ): Response<QuestionApiModel>
}