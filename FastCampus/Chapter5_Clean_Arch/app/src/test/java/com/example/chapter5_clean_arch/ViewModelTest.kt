package com.example.chapter5_clean_arch

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.chapter5_clean_arch.di.appTestModule
import com.example.chapter5_clean_arch.livedata.LiveDataTestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
internal abstract class ViewModelTest : KoinTest {

    operator fun invoke(){
        println("ViewModelTest 생성")
    }

    @get:Rule
    val mockitoRule : MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context : Application

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup(){
        startKoin{
            androidContext(context)
            modules(appTestModule)
            println("startKoin 실행")
        }
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown(){
        stopKoin()
        Dispatchers.resetMain() // MainDispatchers 를 초기화 해주어야 메모리 누수 발생안함
    }

    protected fun <T> LiveData<T>.test() : LiveDataTestObserver<T>{
        val testObserver = LiveDataTestObserver<T>()
        observeForever(testObserver)

        return testObserver
    }


}