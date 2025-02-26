package se.magictechnology.pia13android26feb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import se.magictechnology.pia13android26feb.ui.theme.Pia13android26febTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pia13android26febTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoList()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Button(onClick = {
            val database = Firebase.database
            val myRef = database.getReference("message")

            myRef.setValue("Något annat")
        }) { Text("DO FB") }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pia13android26febTheme {
        TodoList()
    }
}