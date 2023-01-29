package com.example.chapter5_clean_arch.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chapter5_clean_arch.data.entity.ToDoEntity
import com.example.chapter5_clean_arch.domain.todo.*
import com.example.chapter5_clean_arch.domain.todo.DeleteAllToDoItemUseCase
import com.example.chapter5_clean_arch.domain.todo.GetToDoListUseCase
import com.example.chapter5_clean_arch.domain.todo.InsertToDoListUseCase
import com.example.chapter5_clean_arch.domain.todo.UpdateToDoUseCase
import com.example.chapter5_clean_arch.presentation.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.experimental.property.inject

/*
* 필요한 유스케이스
*
* 1. GetToDo
* 2. UpdateTodo
* 3. DeleteAll
* */

@ExperimentalCoroutinesApi
internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val deleteAllToDoItemUseCase: DeleteAllToDoItemUseCase,
) : BaseViewModel() {

    // 상태 표기를 넣어줌
    // 초기상태는 초기화되지 않은 상태
    private val  _toDoListLiveData = MutableLiveData<ToDoListState>(ToDoListState.UnInitialized)
    val todoListLiveData : LiveData<ToDoListState> = _toDoListLiveData

    override fun fetchData() : Job = viewModelScope.launch {
        // 데이터 최신화 ( 로딩 -> 성공 )
        _toDoListLiveData.postValue(ToDoListState.Loading)
        _toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
    }

    fun updateEntity(toDoEntity : ToDoEntity) = viewModelScope.launch {
        // 업데이트 ( 해당 개체를 최신화함 )
        updateToDoUseCase(toDoEntity)
    }

    fun deleteAll() = viewModelScope.launch {
        // 삭제 ( 로딩 -> 삭제 -> 데이터최신화 )
        _toDoListLiveData.postValue(ToDoListState.Loading)
        deleteAllToDoItemUseCase()
        _toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
    }
}