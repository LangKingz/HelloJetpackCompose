package com.example.hellojetpackcompose.Component

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellojetpackcompose.R
import com.example.hellojetpackcompose.SecondaryScreen

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