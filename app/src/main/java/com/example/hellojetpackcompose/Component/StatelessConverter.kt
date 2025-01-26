package com.example.hellojetpackcompose.Component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

private fun convertToFahrenheit(celsius: String) =
    celsius.toDoubleOrNull()?.let {
        (it * 9 / 5) + 32
    }.toString()


@Composable
fun statefulConverter(modifier: Modifier = Modifier){
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    statelessConverterApp(
        input= input,
        output = output,
        onValueChange = {newInput ->
            input = newInput
            output = convertToFahrenheit(newInput)
        }
    )
}

@Composable
fun statelessConverterApp(
    input :String,
    output : String,
    onValueChange : (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier.padding(16.dp)) {
        Text("Celcius")
        OutlinedTextField(
            value = input,
            label = { Text("Celcius") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange
        )
        Text("Farenheint output : $output")
    }
}