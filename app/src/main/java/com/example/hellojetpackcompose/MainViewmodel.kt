package com.example.hellojetpackcompose

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellojetpackcompose.data.client
import com.example.hellojetpackcompose.data.response.Bookmark
import com.example.hellojetpackcompose.data.response.BookmarkItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewmodel : ViewModel() {

    private val _listdata = MutableLiveData<List<BookmarkItem>>()
    val listdata: LiveData<List<BookmarkItem>> = _listdata

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val repository: RepositoryMain = MainRepository(client)

    fun fetch() {

        _loading.value = true

        viewModelScope.launch {

            val data = repository.fetchData()
            Log.d("viewmodel", "fetch: $data")
            if (data.isNotEmpty()) {
                delay(2000)
                _loading.value = false
                _listdata.value = data
            }

        }
    }
}