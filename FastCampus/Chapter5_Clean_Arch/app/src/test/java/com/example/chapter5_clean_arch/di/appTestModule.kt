package com.example.chapter5_clean_arch.di

import com.example.chapter5_clean_arch.data.repository.ToDoRepository
import com.example.chapter5_clean_arch.data.repositoy.TestToDoRepository
import com.example.chapter5_clean_arch.domain.todo.*
import com.example.chapter5_clean_arch.presentation.detail.DetailMode
import com.example.chapter5_clean_arch.presentation.detail.DetailViewModel
import com.example.chapter5_clean_arch.presentation.list.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    // ViewModel
    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode : DetailMode, id : Long) ->
        DetailViewModel(
            detailMode = detailMode,
            id = id,
            get(),
            get(),
            get(),
            get()
        )
    }
    // UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoItemUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }

    // Repository
    single<ToDoRepository> { TestToDoRepository() }

    println("appTestModule 생성됨")
}