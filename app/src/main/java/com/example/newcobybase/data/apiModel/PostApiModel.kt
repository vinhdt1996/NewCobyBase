package com.example.newcobybase.data.apiModel

import com.example.newcobybase.data.model.Post


class PostApiModel(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val body: String?
) {
    fun toPost(): Post {
        return Post(
            userId = userId,
            id = id,
            title = title,
            body = body
        )
    }
}