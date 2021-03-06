package com.kennethmanuel.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kennethmanuel.todoapp.R
import com.kennethmanuel.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick:(Any) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {
    class TodoListViewHolder(var view: View):RecyclerView.ViewHolder(view)

    fun updateTodoList(newTodoList:List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        if(todoList[position].is_done == 1) {
            holder.view.imgEdit.visibility = View.GONE
            holder.view.checkTask.visibility = View.GONE
        } else {
            holder.view.imgEdit.visibility = View.VISIBLE
            holder.view.checkTask.visibility = View.VISIBLE
        }

        holder.view.checkTask.text = todoList[position].title + " " + todoList[position].priority

        holder.view.imgEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditiTodoFragment(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.checkTask.setOnCheckedChangeListener{ compoundButton, isChecked->
            if(isChecked) {
                adapterOnClick(todoList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}