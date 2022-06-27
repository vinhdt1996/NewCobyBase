package com.example.newcobybase.data.apiModel

import com.example.newcobybase.data.model.Question
import com.squareup.moshi.Json

class QuestionApiModel(
    val items: List<Question>?,
    @Json(name = "has_more") val hasMore: Boolean? = false
)