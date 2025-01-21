package com.example.hellojetpackcompose

import com.example.hellojetpackcompose.data.response.Bookmark
import com.example.hellojetpackcompose.data.response.BookmarkItem

interface RepositoryMain {
    suspend fun fetchData():List<BookmarkItem>
}