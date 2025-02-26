package se.magictechnology.pia13android26feb

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TodoList() {



    Column(modifier = Modifier.fillMaxSize()) {
        Text("TODO")


    }
}


@Preview(showBackground = true)
@Composable
fun TodoLiostPreview() {
    TodoList()
}