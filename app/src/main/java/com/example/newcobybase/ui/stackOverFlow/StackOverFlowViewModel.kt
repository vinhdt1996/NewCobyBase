package com.example.newcobybase.ui.stackOverFlow

import androidx.lifecycle.viewModelScope
import com.example.newcobybase.base.ui.BaseViewModel
import com.example.newcobybase.data.model.Question
import com.example.newcobybase.data.repository.StackOverFlowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StackOverFlowViewModel @Inject constructor(private val stackOverFlowRepository: StackOverFlowRepository) :
    BaseViewModel() {

    private var page = 1
    private var pageSize = 2
    private var hasMore = false

    private var _listQuestions = MutableStateFlow<List<Question>>(listOf())
    val listQuestion: StateFlow<List<Question>> = _listQuestions.asStateFlow()

    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) { getData() }
        registerJobFinish()
    }

    private suspend fun getData() {
        val result = stackOverFlowRepository.getQuestion(page, pageSize)
        hasMore = result.hasMore ?: false
        _listQuestions.value = result.items
    }

    fun loadMore() {
        if (hasMore) {
            page++
            showLoadingMore(true)
            parentJob = viewModelScope.launch(handler) {
                getData()
            }
            parentJob?.invokeOnCompletion {
                showLoadingMore(false)
            }
        }
    }

}