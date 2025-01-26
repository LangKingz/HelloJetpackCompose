package com.example.hellojetpackcompose.Component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hellojetpackcompose.data.response.BookmarkItem

@Composable
fun greetingListColumn(data: List<BookmarkItem>) {
//    grid column
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(data) { items ->
            CardImage(items)
        }
    }


}

@Composable
fun greetingList(data: List<BookmarkItem>){
//        list column
    LazyColumn  {
        items(data) {
            Greeting(it)
        }
    }
}
