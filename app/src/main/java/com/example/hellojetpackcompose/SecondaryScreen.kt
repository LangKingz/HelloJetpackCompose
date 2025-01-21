package com.example.hellojetpackcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.hellojetpackcompose.ui.theme.HelloJetpackComposeTheme

class SecondaryScreen: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloJetpackComposeTheme {
               Scaffold(

               ) {innerpadding ->
                   Column(modifier = Modifier.padding(innerpadding).fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                       ) {
                       previewContent()
                   }
               }
            }
        }
    }
}

@Composable
fun previewContent (){
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "Hello , this is Secondary Screen"
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun previewContentPreview(){
    HelloJetpackComposeTheme {
        Scaffold {innerpadding ->
            Column(modifier = Modifier.padding(innerpadding)) {
                previewContent()
            }
        }
    }
}