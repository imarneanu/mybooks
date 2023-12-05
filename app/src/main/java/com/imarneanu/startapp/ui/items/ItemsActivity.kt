package com.imarneanu.startapp.ui.items

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.imarneanu.startapp.ui.theme.StartAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsActivity : ComponentActivity() {

    val model: ItemsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StartAppTheme {
                ItemsScreen(model.state.collectAsState().value, model::onEvent)
            }
        }
    }
}
