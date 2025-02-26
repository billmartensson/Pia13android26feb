package se.magictechnology.pia13android26feb

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoStart(todoviewmodel : TodoViewModel = viewModel()) {

    val loggedin by todoviewmodel.loggedin.collectAsState()

    if(loggedin) {
        TodoNav(todoviewmodel)
    } else {
        Login(todoviewmodel)
    }
}