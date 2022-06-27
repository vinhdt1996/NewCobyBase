package com.example.newcobybase.data.api

import com.example.newcobybase.data.apiModel.PostApiModel
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("/posts")
    suspend fun getAllPosts(): Response<List<PostApiModel>>
}