package se.magictechnology.pia13android26feb

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun Login(todoviewmodel : TodoViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column {
        Text("LOGIN")

        TextField(label = { Text("Email") }, value = email, onValueChange = {email = it})

        TextField(label = { Text("Password") }, value = password, onValueChange = {password = it})

        Button(onClick = {
            todoviewmodel.login(email, password)
        }) {
            Text("LOGIN")
        }

        Button(onClick = {
            todoviewmodel.register(email, password)
        }) {
            Text("REGISTER")
        }

    }
}