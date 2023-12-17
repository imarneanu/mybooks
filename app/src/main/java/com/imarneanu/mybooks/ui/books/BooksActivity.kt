package com.imarneanu.mybooks.ui.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.imarneanu.mybooks.ui.theme.MyBooksTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksActivity : ComponentActivity() {

    val model: BooksViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyBooksTheme {
                BooksScreen(model.state.collectAsState().value, model::onEvent)
            }
        }
    }
}
