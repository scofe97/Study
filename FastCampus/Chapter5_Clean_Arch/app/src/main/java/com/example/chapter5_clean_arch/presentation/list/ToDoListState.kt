package com.example.chapter5_clean_arch.presentation.list

import com.example.chapter5_clean_arch.data.entity.ToDoEntity

sealed class ToDoListState {

    // 초기화 안된 상태
    object UnInitialized : ToDoListState()

    // 로딩 상태
    object Loading : ToDoListState()

    // 성공
    data class Success(
        val toDoList : List<ToDoEntity>
    ) : ToDoListState()

    // 실패 상태
    object Error : ToDoListState()
}