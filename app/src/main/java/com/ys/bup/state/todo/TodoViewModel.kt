package com.ys.bup.state.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    // private state
    private var currentEditPosition by mutableStateOf(-1)

    // state
    var todoItems by mutableStateOf(listOf<TodoItem>())
        private set

    // state
    val currentEditItem: TodoItem?
        get() = todoItems.getOrNull(currentEditPosition)

    fun addItem(item: TodoItem) {
        todoItems = todoItems + listOf(item)
    }

    fun removeItem(item: TodoItem) {
        todoItems = todoItems.toMutableList().also {
            it.remove(item)
        }

        onEditDone()
    }

    // event: onEditItemSelected
    fun onEditItemSelected(item: TodoItem) {
        currentEditPosition = todoItems.indexOf(item)
    }

    // event: onEditDone
    fun onEditDone() {
        currentEditPosition = -1
    }

    // event: onEditItemChange
    fun onEditItemChange(item: TodoItem) {
        val currentItem = requireNotNull(currentEditItem)
        require(currentItem.id == item.id) {
            "You can only change an item with the same id as currentEditItem"
        }

        todoItems = todoItems.toMutableList().also {
            it[currentEditPosition] = item
        }
    }
}