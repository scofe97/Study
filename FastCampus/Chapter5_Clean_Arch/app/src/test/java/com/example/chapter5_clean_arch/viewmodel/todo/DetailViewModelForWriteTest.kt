package com.example.chapter5_clean_arch.viewmodel.todo

import com.example.chapter5_clean_arch.ViewModelTest
import com.example.chapter5_clean_arch.data.entity.ToDoEntity
import com.example.chapter5_clean_arch.presentation.detail.DetailMode
import com.example.chapter5_clean_arch.presentation.detail.DetailViewModel
import com.example.chapter5_clean_arch.presentation.detail.ToDoDetailState
import com.example.chapter5_clean_arch.presentation.list.ListViewModel
import com.example.chapter5_clean_arch.presentation.list.ToDoListState
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

/*
* [DetailViewModel 을 테스트하기 위한 Unit Test Class
*
* 1. test viewModel fetch
* 2. test insert todo
*
* */

internal class DetailViewModelForWriteTest : ViewModelTest() {

    private val id = 0L

    private val detailViewModel by inject<DetailViewModel> { parametersOf(DetailMode.WRITE, id) }
    private val listViewModel by inject<ListViewModel>()

    private val todo = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )

    @Test
    fun `test viewModel fetch`() = runBlockingTest {
        val testObservable = detailViewModel.toDoDetailLiveData.test()

        detailViewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Write
            )
        )
    }

    @Test
    fun `test insert todo`() = runBlockingTest {
        val detailTestObservable = detailViewModel.toDoDetailLiveData.test()
        val listTestObservable = listViewModel.todoListLiveData.test()

        detailViewModel.writeToDo(
            title = todo.title,
            description = todo.description
        )

        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )
        )

        assert(detailViewModel.detailMode == DetailMode.DETAIL)
        assert(detailViewModel.id == id)

        // 뒤로나가서 리스트 보기
        listViewModel.fetchData()
        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(
                    listOf(
                        todo
                    )
                )
            )
        )
    }


}