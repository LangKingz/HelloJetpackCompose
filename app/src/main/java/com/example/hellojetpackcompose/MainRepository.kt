package com.example.hellojetpackcompose

import com.example.hellojetpackcompose.data.client
import com.example.hellojetpackcompose.data.response.Bookmark
import com.example.hellojetpackcompose.data.response.BookmarkItem

class MainRepository(private val client: client):RepositoryMain {
    override suspend fun fetchData(): List<BookmarkItem> {
        val response = client.komryuget.getBookmark()
        val data = response.bookmark
        return data as List<BookmarkItem>
    }
}