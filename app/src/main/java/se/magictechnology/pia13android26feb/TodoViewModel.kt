package se.magictechnology.pia13android26feb

import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


data class Todo(val title : String? = null, val done : Boolean? = null)


class TodoViewModel : ViewModel() {

    val database = Firebase.database.reference


    fun addTodo(todotitle : String) {
        val newtodo = Todo(todotitle, false)

        database.child("androidtodo").push().setValue(newtodo)
    }

    fun loadTodo() {

    }

}