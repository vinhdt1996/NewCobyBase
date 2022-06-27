package com.example.newcobybase.ui.jsonPlaceHolder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newcobybase.base.ui.BaseViewModel
import com.example.newcobybase.data.model.Post
import com.example.newcobybase.data.repository.JsonPlaceHolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JsonPlaceHolderViewModel @Inject constructor(private val jsonPlaceHolderRepository: JsonPlaceHolderRepository) :
    BaseViewModel() {

    private var _listPosts = MutableLiveData<List<Post>>()
    val listPosts: LiveData<List<Post>>
        get() = _listPosts

    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val posts = jsonPlaceHolderRepository.getAllPosts()
            _listPosts.postValue(posts)
        }
        registerJobFinish()
    }
}