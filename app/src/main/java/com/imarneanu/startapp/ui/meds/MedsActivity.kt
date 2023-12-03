package com.imarneanu.startapp.ui.meds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imarneanu.startapp.ui.theme.StartAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MedsActivity : ComponentActivity() {

    val model: MedsViewModel by viewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = mutableStateOf("")
        val names = mutableStateOf(listOf<String>())

        setContent {
            StartAppTheme {
//                val model = getViewModel<MyListViewModel>()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = name.value,
                            onValueChange = { text ->
                                name.value = text
                            })
                        Button(onClick = {
                            if (name.value.isBlank()) return@Button
                            names.value = names.value + name.value
                            model.addMedicine(name.value)
                            name.value = ""
                        }) {
                            Text(text = "Add")
                        }
                    }
                    LazyColumn {
                        items(names.value) { currentName ->
                            Text(currentName)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(name: String) {
    Row {
        Text(text = name, Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    StartAppTheme {
        ListItem("Items")
    }
}
