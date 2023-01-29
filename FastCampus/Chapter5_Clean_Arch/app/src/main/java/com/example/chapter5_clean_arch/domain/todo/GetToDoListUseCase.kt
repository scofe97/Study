package com.example.chapter5_clean_arch.domain.todo

import com.example.chapter5_clean_arch.data.entity.ToDoEntity
import com.example.chapter5_clean_arch.data.repository.ToDoRepository
import com.example.chapter5_clean_arch.domain.Usecase

internal class GetToDoListUseCase( private val toDoRepository : ToDoRepository ) : Usecase {

    suspend operator fun invoke() : List<ToDoEntity> {
        return toDoRepository.getToDoList()
    }
}