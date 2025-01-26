package com.example.hellojetpackcompose.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellojetpackcompose.R
import com.example.hellojetpackcompose.data.response.BookmarkItem

@Composable
fun CardImage(Bookmark: BookmarkItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            com.skydoves.landscapist.coil.CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                imageModel = { Bookmark.imageUrl },
                failure = {
                    Image(
                        painter = painterResource(R.drawable.error),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                },
            )
            Text(
                text = Bookmark.title.toString(),
                maxLines = 1,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .fillMaxWidth()
                    .padding(8.dp),
            )
        }
    }
}