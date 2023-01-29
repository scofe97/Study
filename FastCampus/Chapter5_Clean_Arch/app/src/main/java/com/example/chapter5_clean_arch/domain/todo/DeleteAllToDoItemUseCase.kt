package com.example.chapter5_clean_arch.domain.todo

import com.example.chapter5_clean_arch.data.entity.ToDoEntity
import com.example.chapter5_clean_arch.data.repository.ToDoRepository
import com.example.chapter5_clean_arch.domain.Usecase

internal class DeleteAllToDoItemUseCase(private val toDoRepository : ToDoRepository ) : Usecase {

    suspend operator fun invoke()  {
        return toDoRepository.deleteAll()
    }
}