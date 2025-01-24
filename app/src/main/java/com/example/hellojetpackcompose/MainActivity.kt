package com.example.hellojetpackcompose

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.GlideImage
import com.example.hellojetpackcompose.data.response.Bookmark
import com.example.hellojetpackcompose.data.response.BookmarkItem
import com.example.hellojetpackcompose.ui.theme.HelloJetpackComposeTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloJetpackComposeTheme(
                dynamicColor = false
            ) {
                HellojetPackComposeApp()
            }
        }
    }
}

@Composable
fun headerProfile(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .padding(10.dp)
            .clickable(onClick = {
                val intent = Intent(context, SecondaryScreen::class.java)
                context.startActivity(intent)
            }),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.compose),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .border(2.dp, Color.Green, CircleShape)
                    .clip(CircleShape)
                    .size(60.dp)
            )
            Spacer(modifier.width(8.dp))
            Column(
                modifier = modifier.weight(1f),
            ) {
                Text(
                    text = "$name",
                    fontSize = 20.sp
                )
                Text(
                    text = "Student",
                    fontSize = 15.sp
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.ArrowForwardIos,
                    contentDescription = "arrow"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HellojetPackComposeApp(viewmodel: MainViewmodel = MainViewmodel()) {
    val listData by viewmodel.listdata.observeAsState(initial = emptyList())
    val loading by viewmodel.loading.observeAsState(initial = false)


    LaunchedEffect(Unit) {
        viewmodel.fetch()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pustaka",
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "search"
                        )
                    }
                }
            )
        },
        bottomBar = {

        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
//            headerProfile("langKing")
//            if (loading){
//                CircularProgressIndicator(
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//            greetingList(listData)
            statefullCounter()
        }
    }
}

@Composable
fun statelessCounter(
    count: Int,
    onClik: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        Text("Count : $count Times")
        Button(onClick = onClik) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun statefullCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    statelessCounter(
        count = count,
        onClik = { count++ },
        modifier = modifier
    )
}

@Composable
fun greetingList(data: List<BookmarkItem>) {

//    list column
//    LazyColumn {
//        items(data) {
//            Greeting(it)
//        }
//    }

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


@Composable
fun Greeting(Bookmark: BookmarkItem) {
    var isExpanded = remember { mutableStateOf(false) }

    val animatedSizeOp = animateDpAsState(
        targetValue = if (isExpanded.value) 120.dp else 80.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )

    )

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            com.skydoves.landscapist.coil.CoilImage(
                imageModel = { Bookmark.imageUrl },
                modifier = Modifier
                    .size(animatedSizeOp.value)
                    .clip(RoundedCornerShape(10.dp)),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${Bookmark.title}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(text = "Welcome to Dicoding!")
            }
            IconButton(onClick = { isExpanded.value = !isExpanded.value }) {
                Icon(
                    imageVector = if (isExpanded.value) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                    contentDescription = if (isExpanded.value) "Show less" else "Show more"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    HelloJetpackComposeTheme(
        dynamicColor = false
    ) {
        HellojetPackComposeApp()
    }
}

