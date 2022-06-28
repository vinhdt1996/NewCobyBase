package com.example.newcobybase.ui.jsonPlaceHolder

import androidx.lifecycle.viewModelScope
import com.example.newcobybase.base.ui.BaseViewModel
import com.example.newcobybase.data.model.Post
import com.example.newcobybase.data.repository.JsonPlaceHolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JsonPlaceHolderViewModel @Inject constructor(private val jsonPlaceHolderRepository: JsonPlaceHolderRepository) :
    BaseViewModel() {

    private var _listPosts = MutableStateFlow<List<Post>>(listOf())
    val listPosts: StateFlow<List<Post>> = _listPosts.asStateFlow()

    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val posts = jsonPlaceHolderRepository.getAllPosts()
            _listPosts.value = posts
        }
        registerJobFinish()
    }
}