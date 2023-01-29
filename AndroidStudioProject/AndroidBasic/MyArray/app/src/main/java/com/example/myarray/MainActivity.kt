package com.example.myarray

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            val names = arrayOf("홍길동1", "홍길동2", "홍길동3")
            val names2 = Array<String>(3,{index -> "홍길동${index+1}"})
            val names3 =  arrayOfNulls<String>(3) // 3칸짜리 배열 빈공간 null값
            val names4 = emptyArray<String>() // 크기가 비어있음

            output1.append("\n names : ${Arrays.toString(names)}")
            output1.append("\n names2 : ${Arrays.toString(names2)}")
            output1.append("\n names3 : ${Arrays.toString(names3)}")
            output1.append("\n names4 : ${Arrays.toString(names4)}")

            val digits = intArrayOf(1,2,3)
            // digits.set(2, 4) // 배열의 수정 인덱스2를 4로
            digits[2] = 4
            // val aDigit = digits.get(2)
            val aDigit = digits[2]

            var digitCount = digits.count()
            val digitSize = digits.size
            output1.append("\ndigits : 배열 크기 : $digitCount")
            output1.append("\ndigits : 배열 크기 : $digitSize")

            val digits2 = digits.plus(5) // 배열값 추가 (새로운 배열반환)
            digitCount = digits.count()
            output1.append("\ndigits : 배열 크기 : $digitCount")

            val aIndex = digits2.indexOf(5) // 5의 인덱스 번호
            val digits3 = digits2.sliceArray(1..aIndex) // 범위로 배열자름 새로운 배열줌
            output1.append("\n 배열3 : ${digits3.contentToString()}")

            digits.forEach { i ->
                output1.append("\n ${i}")
            }
            digits.forEachIndexed() { index, i ->
                output1.append("\n#${index} : ${i}")
            }

           // 기존의 for문 문법하고 좀 다름
            // forEach는 이것을 단순화한 거임
            output1.append("\n2번째 영상")
            var elemIndex = 0
            for(elem in digits){
                output1.append("\n#${elemIndex} : ${elem}")
                elemIndex += 1
            }

            // 이터레이터 (while문과 자주쓰임)
            var iter = digits.iterator()
            elemIndex = 0
            while(iter.hasNext()){
                val elem = iter.next()
                output1.append("\n#${elemIndex} : ${elem}")
                elemIndex += 1
            }

            // 배열정렬
            val sortedArray = digits.sortedArray()  // 오름차순
            val sortedArray2 = digits.sortedArrayDescending() // 내림차순

            digits.filter{ elem -> elem > 1}.forEach{
                output1.append("\n#원소 : ${it}")
            } // elem이 1보다 큰경우 줌 (필터이후, 하나씩 다 출력함)

        }

        button2.setOnClickListener {
            val names = listOf("홍길동1", "홍길동2", "홍길동3")
            val names2 = List<String>(3){ index ->"홍길동${index+1}" }
            // 람다식이 함수의 마지막 파라미터이면 밖으로 뺄수있다.
            // names.add() 에러
            // 기본 리스트는 immutable (수정이 불가)
            val names3 = mutableListOf<String>("홍길동1", "홍길동2", "홍길동3")
            val names4 = emptyList<String>() // 빈 리스트 생성
            val names5 = arrayListOf<String>() // 내부적으로는 배열처럼 동작함 (가장많이씀)
            val names6 = ArrayList<String>() // 클래스 방식으로도 생성
            names3.add("홍길동4")
            names3[1] = "홍길동5"
            output1.append("\n${names3.joinToString()}")

            if (names3.contains("홍길동5")){ //홍길동5가 있는가?
                val aIndex = names3.indexOf("홍길동5") // 1 5 3 4 이렇게 있음
                val names4 = names3.slice(0..aIndex)
                output1.append("\n${names4.joinToString()}")

            }
            // 리스트역시 forEach나 이터레이터로 가능, 기타기능도 포함


        }
    }
}