package com.kennethmanuel.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.kennethmanuel.todoapp.model.Todo
import com.kennethmanuel.todoapp.model.TodoDatabase
import com.kennethmanuel.todoapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private var job = Job()
    val todoLD = MutableLiveData<Todo>()

    fun fetch(uuid:Int) {
        launch {
            val db = buildDB(getApplication())
            todoLD.value = db.todoDao().selectTodo(uuid)
        }
    }

    fun addTodo(todo: Todo) {
        launch {
            val db = buildDB(getApplication())
            db.todoDao().insertAll(todo)
        }
    }

    fun update(title:String, notes:String, priority:Int, is_done:Int, uuid:Int) {
        launch {
            val db = buildDB(getApplication())
            db.todoDao().update(title, notes, priority, is_done, uuid)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}