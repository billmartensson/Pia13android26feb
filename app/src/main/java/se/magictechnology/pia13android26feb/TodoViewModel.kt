package se.magictechnology.pia13android26feb

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class Todo(var fbid : String? = null, val title : String? = null, val done : Boolean? = null)


class TodoViewModel : ViewModel() {

    val database = Firebase.database.reference

    private val _alltodo = MutableStateFlow(listOf<Todo>())
    val alltodo: StateFlow<List<Todo>> = _alltodo.asStateFlow()

    fun addTodo(todotitle : String) {
        val newtodo = Todo(title = todotitle, done =  false)

        database.child("androidtodo").push().setValue(newtodo)

        loadTodo()
    }

    fun loadTodo() {
        database.child("androidtodo").get().addOnSuccessListener { todosnapshot ->

            var loadedtodo = mutableListOf<Todo>()
            todosnapshot.children.forEach { childsnapshot ->
                /*
                val todo = childsnapshot.getValue<Todo>()
                if(todo != null) {
                    todo.fbid = childsnapshot.key
                    loadedtodo.add(todo)
                }
                */

                childsnapshot.getValue<Todo>()?.let { todo ->
                    todo.fbid = childsnapshot.key
                    loadedtodo.add(todo)
                }

            }

            _alltodo.value = loadedtodo

        }
    }

    fun changeDone(changetodo : Todo) {
        var fbid = ""
        changetodo.fbid?.let {
            fbid = it
        }
        if(fbid == "") {
            // FAIL
            return
        }

        var done = false
        changetodo.done?.let {
            done = it
        }
        done = !done

        database
            .child("androidtodo")
            .child(fbid)
            .child("done")
            .setValue(done)

        loadTodo()
    }

    fun deleteTodo(deltodo : Todo) {
        var fbid = ""
        deltodo.fbid?.let {
            fbid = it
        }
        if(fbid == "") {
            // FAIL
            return
        }

        database
            .child("androidtodo")
            .child(fbid)
            .removeValue()

        loadTodo()
    }


}