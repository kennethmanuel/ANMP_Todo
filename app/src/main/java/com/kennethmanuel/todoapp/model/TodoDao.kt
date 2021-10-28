package com.kennethmanuel.todoapp.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    suspend fun selectAllTodo():List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    suspend fun selectTodo(id:Int):Todo

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority, is_done=:is_done WHERE uuid=:uuid")
    suspend fun update(title:String, notes:String, priority:Int, is_done:Int, uuid:Int)

    @Delete
    suspend fun deleteTodo(todo:Todo)
}