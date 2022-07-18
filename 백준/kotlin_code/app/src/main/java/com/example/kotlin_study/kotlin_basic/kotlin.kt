package com.example.kotlin_study



fun main(){

    // val -> 값이 변경되지 않음 (재할당 불가)
    // var -> 값이 변경될 수 있는 변수에 사용

    var count : Int = 10
    count = 15

    val languageName : String = "Kotlin"
    // languageName = "~~" 불가



    // 유형 추론
    // val languageName = "Kotlin" 이렇게 적어도 알아서 string으로 안다는 것
    val upperCaseName = languageName.toUpperCase()

    // 실패 경우 -> inc() 는 Int 형 함수임
    //languageName.inc()



    // null 안전
    val language2 :String? = null


    // 익명함수 (람다식)
    val stringLengthFunc: (String) -> Int = { input ->
        input.length
    }

    val stringLength : Int = stringLengthFunc(languageName)
    println(stringLength)


    // 고차 함수 ( 다른함수를 인수로 취할 수 있다)
    fun stringMapper(str: String, mapper: (String) -> Int) : Int{
        return mapper(str)
    }

    println(stringMapper("응애", stringLengthFunc))
}