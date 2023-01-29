package com.example.chapter5_clean_arch.livedata

import androidx.lifecycle.Observer
import com.example.chapter5_clean_arch.presentation.detail.ToDoDetailState
import com.example.chapter5_clean_arch.presentation.list.ToDoListState
import java.lang.AssertionError
import java.util.*

class LiveDataTestObserver<T> : Observer<T> {

    operator fun invoke(){
        println("LiveDataTestObserver 생성")
    }

    private val values : MutableList<T> = mutableListOf()

    override fun onChanged(t: T) {
        values.add(t)
    }

    fun assertValueSequence(sequence: List<T>) : LiveDataTestObserver<T>{
        var i = 0
        val actualIterator = values.iterator()
        val expectedIterator = sequence.iterator()

        var actualNext : Boolean
        var expectedNext : Boolean

        while(true){

            actualNext = actualIterator.hasNext()
            expectedNext = expectedIterator.hasNext()

            if(!actualNext || !expectedNext) break

            val actual : T = actualIterator.next()
            val expected : T = expectedIterator.next()

            println("actual : $actual ,expected : $expected, index : $i")

            if(actual != expected){
                throw AssertionError{"actual : $actual ,expected : $expected, index : $i"}
            }

            i++
        }

        if (actualNext){
            throw AssertionError("More Values received than expected $i")
        }

        if(expectedNext){
            throw AssertionError("Fewer Values received than expected $}")
        }

        return this
    }

}