package com.example.newcobybase.data.source.remote

import com.example.newcobybase.base.network.BaseRemoteDataSource
import com.example.newcobybase.base.network.NetworkResult
import com.example.newcobybase.data.api.JsonPlaceHolderApi
import com.example.newcobybase.data.apiModel.PostApiModel
import javax.inject.Inject

class JsonPlaceHolderRemoteDataSource @Inject constructor(private val jsonPlaceHolderApi: JsonPlaceHolderApi) :
    BaseRemoteDataSource() {

    suspend fun getAllPosts(): NetworkResult<List<PostApiModel>> {
        return callApi { jsonPlaceHolderApi.getAllPosts() }
    }

}