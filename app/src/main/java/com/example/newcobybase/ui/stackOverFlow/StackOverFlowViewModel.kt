package com.example.newcobybase.ui.stackOverFlow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newcobybase.base.ui.BaseViewModel
import com.example.newcobybase.data.model.Question
import com.example.newcobybase.data.repository.StackOverFlowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StackOverFlowViewModel @Inject constructor(private val stackOverFlowRepository: StackOverFlowRepository) :
    BaseViewModel() {

    private var page = 1
    private var pageSize = 2
    private var hasMore = false

    private var _listQuestions = MutableLiveData<List<Question>?>()
    val listQuestion: LiveData<List<Question>?>
        get() = _listQuestions

    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) { getData() }
        registerJobFinish()
    }

    private suspend fun getData() {
        val result = stackOverFlowRepository.getQuestion(page, pageSize)
        hasMore = result.hasMore ?: false
        _listQuestions.postValue(result.items)
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