package se.magictechnology.pia13android26feb

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class Todo(var fbid : String? = null, val title : String? = null, val done : Boolean? = null)


class TodoViewModel : ViewModel() {

    val database = Firebase.database.reference

    private val _loggedin = MutableStateFlow(false)
    val loggedin = _loggedin.asStateFlow()

    private val _alltodo = MutableStateFlow(listOf<Todo>())
    val alltodo: StateFlow<List<Todo>> = _alltodo.asStateFlow()

    init {
        checklogin()
    }

    fun checklogin() {
        if(Firebase.auth.currentUser == null) {
            _loggedin.value = false
        } else {
            _loggedin.value = true
        }
    }

    fun login(email : String, password : String) {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            checklogin()
        }.addOnFailureListener {
            // VISA FEL
        }
    }

    fun register(email : String, password : String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            checklogin()
        }.addOnFailureListener {
            // VISA FEL
        }
    }

    fun logout() {
        Firebase.auth.signOut()
        checklogin()
    }

    fun addTodo(todotitle : String) {
        val newtodo = Todo(title = todotitle, done =  false)

        Firebase.auth.currentUser?.let { currentUser ->
            database
                .child("androidtodo")
                .child(currentUser.uid)
                .push()
                .setValue(newtodo)

            loadTodo()
        }

    }

    fun loadTodo() {
        var uid = ""
        Firebase.auth.currentUser?.let {
            uid = it.uid
        }
        if(uid == "") {
            return
        }

        database.child("androidtodo").child(uid).get().addOnSuccessListener { todosnapshot ->

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

        var uid = ""
        Firebase.auth.currentUser?.let {
            uid = it.uid
        }
        if(uid == "") {
            return
        }

        database
            .child("androidtodo")
            .child(uid)
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

        var uid = ""
        Firebase.auth.currentUser?.let {
            uid = it.uid
        }
        if(uid == "") {
            return
        }

        database
            .child("androidtodo")
            .child(uid)
            .child(fbid)
            .removeValue()

        loadTodo()
    }


}