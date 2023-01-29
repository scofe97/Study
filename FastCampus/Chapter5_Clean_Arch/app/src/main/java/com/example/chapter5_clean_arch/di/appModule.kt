package com.example.chapter5_clean_arch.di

import android.content.Context
import androidx.room.Room
import com.example.chapter5_clean_arch.data.local.db.ToDoDatabase
import com.example.chapter5_clean_arch.data.repository.DefaultToDoRepository
import com.example.chapter5_clean_arch.data.repository.ToDoRepository
import com.example.chapter5_clean_arch.domain.todo.*
import com.example.chapter5_clean_arch.domain.todo.DeleteAllToDoItemUseCase
import com.example.chapter5_clean_arch.domain.todo.DeleteToDoItemUseCase
import com.example.chapter5_clean_arch.domain.todo.GetToDoItemUseCase
import com.example.chapter5_clean_arch.domain.todo.GetToDoListUseCase
import com.example.chapter5_clean_arch.domain.todo.InsertToDoItemUseCase
import com.example.chapter5_clean_arch.domain.todo.InsertToDoListUseCase
import com.example.chapter5_clean_arch.domain.todo.UpdateToDoUseCase
import com.example.chapter5_clean_arch.presentation.detail.DetailMode
import com.example.chapter5_clean_arch.presentation.detail.DetailViewModel
import com.example.chapter5_clean_arch.presentation.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single {Dispatchers.Main}
    single {Dispatchers.IO}

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
    single<ToDoRepository> { DefaultToDoRepository(get(), get()) }

    single { provideDB(androidContext()) }
    single { provideToDoDao(get()) }

    println("appTestModule 생성됨")
}

internal fun provideDB(context : Context) : ToDoDatabase =
    Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()

internal fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()