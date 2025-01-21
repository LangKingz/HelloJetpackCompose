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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun HellojetPackComposeApp(viewmodel: MainViewmodel = MainViewmodel()) {
    val listData by viewmodel.listdata.observeAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        viewmodel.fetch()
    }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            headerProfile("langKing")
            greetingList(listData)
        }
    }
}

@Composable
fun greetingList(data: List<BookmarkItem>) {
    LazyColumn {
        items(data) {
            Greeting(it)
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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cosmic345.co/wp-content/uploads/2025/01/Sono-MonbanCUnetnoise_scaleLevel3width-600.jpg")
                    .build(),
                contentDescription = "Gambar Bookmark",
                modifier = Modifier.size(animatedSizeOp.value),
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

