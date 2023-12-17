package com.imarneanu.mybooks.ui.item

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.imarneanu.mybooks.ui.theme.MyBooksTheme

class MyItemActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val count = mutableStateOf(0)
        setContent {
            MyBooksTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = count.value.toString(), fontSize = 30.sp)
                    Button(onClick = { count.value++ }) {
                        Text(text = "CLick me!")
                    }
                }
            }
        }
    }
}

@Composable
fun List(items: String) {
    Text(text = items, Modifier.fillMaxWidth())
    LazyColumn(modifier = Modifier.fillMaxWidth()) {

    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    MyBooksTheme {
        List("Items")
    }
}

