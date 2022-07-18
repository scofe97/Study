package com.example.funtion2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            //val result = add(10,10)

            val addFunc= {a:Int, b:Int-> a+b}
            //val result = addFunc(10,10)
            val reuslt = calc(10,10,addFunc)

            val addFunc2 = getOperator("add")
            val result2 = calc(20,20,addFunc2!!)

            output1.text = "결과 : "

            val multuply = {a:Int, b:Int -> a*b}
            val multiply2:(Int,Int) ->Int = {a,b -> a*b}

            var show1 = {println("show 함수 호출됨")} // 반환값이나, 파라미터 없을시 생략이 가능함
            var show2:()->Unit = {println("show 함수 호출됨")} // 위와 동일함

            // 익명함수 인자 전달방법
            val sum = fun (a:Int, b:Int): Int{
                return a+b
            }
            sum(10,10)

            // 파라미터가 없고 값을 반환하는 함수
            doAction(fun() :Int{
                println("전달된 함수 호출됨")
                return 10
            })

            doAction{->
                println("전달된 함수 호출됨")
                return@doAction 10   // 명확한 반환 (범위지정)
            }

            doAction{->
                println("전달된 함수 호출됨")
                10
            }
        }
    }

    fun doAction(action:()->Int){
        println("doAction 호출됨")
        val result =  action()

    }



    fun show():Unit{  // Unit은 생략이 가능함 자바의 void 생각
        println("show 함수 호출됨")
    }

    fun getOperator(name:String): ((Int,Int)->Int)?{
        var oper : ((Int,Int) ->Int)? = null
        if(name =="add"){
            oper = {a:Int, b:Int -> a + b}
        }else if(name == "subtract"){
            oper = {a:Int, b:Int -> a-b}
        }

        return oper
    }

    fun calc(first:Int, second:Int, oper:(Int,Int)->Int) : Int{
        return oper(first, second)
    }

    fun add(a:Int, b:Int) = a + b
}