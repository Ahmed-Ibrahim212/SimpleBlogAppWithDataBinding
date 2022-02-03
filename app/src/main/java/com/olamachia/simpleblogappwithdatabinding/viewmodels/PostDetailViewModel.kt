package com.olamachia.simpleblogappwithdatabinding.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olamachia.simpleblogappwithdatabinding.model.MainRepository
import com.olamachia.simpleblogappwithdatabinding.model.domain.Comment
import com.olamachia.simpleblogappwithdatabinding.util.DataState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



class PostDetailViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private var _dataState: MutableLiveData<DataState<List<Comment>>> = MutableLiveData()
    val commentData: LiveData<DataState<List<Comment>>>
        get() = _dataState

    private val _commentPostData: MutableLiveData<DataState<List<Comment>>> = MutableLiveData()
    val commentPostData: LiveData<DataState<List<Comment>>>
        get() = _commentPostData


    fun getPostComments(postId: Int) {
        viewModelScope.launch {
            mainRepository.getPostComments(postId).collect {
                _dataState.value = it
            }
        }
    }

    fun getComments(postId: Int){
        viewModelScope.launch {
            mainRepository.getComments(postId).collect{
                _dataState.value = it
            }
        }
    }

    fun insertComment(comment: Comment) {
        viewModelScope.launch {
            mainRepository.insertComments(comment)
        }
    }
}