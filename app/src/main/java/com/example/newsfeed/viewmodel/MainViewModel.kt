package com.example.newsfeed.viewmodel

import android.app.Application
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import com.example.newsfeed.repository.NewsRepository
import com.example.newsfeed.data.News

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: NewsRepository by lazy {
        NewsRepository(app)
    }

    fun initParameters() {
        repository.isLoading.value = false
    }

//    // パターン１
//    var newsList = MutableLiveData<List<News>>()
//    fun getNews(searchWord: String) {
//        searchWord?.let {
//            viewModelScope.launch {
//                newsList.value = repository.getNews(searchWord)
//            }
//        }
//    }

    //パターン２
    val searchWord = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = Transformations.switchMap(repository.isLoading){
//         repository.isLoading
//        }

    fun getNews(word: String){
        searchWord.value = word
    }
    fun getState(): LiveData<Boolean> {
        return  repository.isLoading
    }

    val newsList: LiveData<List<News>> = Transformations.switchMap(searchWord){ word ->
        word?.let {
            liveData {
                emit(repository.getNews(it))
            }
        }
    }



}