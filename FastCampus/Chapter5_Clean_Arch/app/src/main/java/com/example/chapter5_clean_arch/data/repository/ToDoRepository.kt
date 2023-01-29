package com.example.chapter5_clean_arch.data.repository

import com.example.chapter5_clean_arch.data.entity.ToDoEntity

/**
 * 1. insertToDoList
 * 2. getToDoList
 * 3. updateToDoList
 */

interface ToDoRepository {

    suspend fun getToDoList() : List<ToDoEntity>

    suspend fun getToDoItem(itemId: Long): ToDoEntity?

    suspend fun insertToDoItem(toDoItem : ToDoEntity) : Long

    suspend fun insertToDoList(toDoList : List<ToDoEntity>)

    suspend fun updateToDoList(toDoItem: ToDoEntity)

    suspend fun deleteAll()

    suspend fun deleteToDoItem(id : Long)
}