package se.magictechnology.pia13android26feb

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoList(todoviewmodel : TodoViewModel = viewModel()) {

    var addtodotitle by remember { mutableStateOf("") }

    val alltodo by todoviewmodel.alltodo.collectAsState()

    LaunchedEffect(true) {
        todoviewmodel.loadTodo()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("TODO")


        Row {
            TextField(value = addtodotitle, onValueChange = {addtodotitle = it})

            Button(onClick = {
                todoviewmodel.addTodo(addtodotitle)
            }) {
                Text("ADD")
            }
        }

        LazyColumn {
            items(alltodo) {
                Row(modifier = Modifier.clickable {
                    todoviewmodel.changeDone(it)
                }) {
                    Text(it.title!!)

                    if(it.done == true) {
                        Text("DONE")
                    } else {
                        Text("NOT DONE")
                    }

                    Button(onClick = {
                        todoviewmodel.deleteTodo(it)
                    }) {
                        Text("DELETE")
                    }
                }
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun TodoLiostPreview() {
    TodoList()
}