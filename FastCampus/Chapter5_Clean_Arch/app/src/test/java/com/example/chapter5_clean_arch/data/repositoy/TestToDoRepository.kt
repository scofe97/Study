package com.example.chapter5_clean_arch.data.repositoy

import com.example.chapter5_clean_arch.data.entity.ToDoEntity
import com.example.chapter5_clean_arch.data.repository.ToDoRepository

class TestToDoRepository : ToDoRepository {

    operator fun invoke(){
        println("TestToDoRepository 생성")
    }


    private val toDoList : MutableList<ToDoEntity> = mutableListOf()

    override suspend fun getToDoList(): List<ToDoEntity> {
        return toDoList
    }

    override suspend fun insertToDoItem(toDoItem: ToDoEntity) : Long {
        this.toDoList.add(toDoItem)
        return toDoItem.id
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
        this.toDoList.addAll(toDoList)
    }



    override suspend fun updateToDoList(toDoItem: ToDoEntity) : Boolean{
        val foundToDoEntity = toDoList.find{it.id == toDoItem.id}
        if(foundToDoEntity == null) return false
        else{
            this.toDoList[toDoList.indexOf((foundToDoEntity))] = toDoItem
            return true
        }
    }

    override suspend fun getToDoItem(itemId: Long): ToDoEntity? {
        return toDoList.find{ it.id == itemId}
    }

    override suspend fun deleteAll() {
        toDoList.clear()
    }

    override suspend fun deleteToDoItem(id: Long): Boolean {
        val foundToDoEntity = toDoList.find{it.id == id}

        if(foundToDoEntity == null) return false
        else{
            this.toDoList.removeAt(toDoList.indexOf(foundToDoEntity))
            return true
        }

    }

}