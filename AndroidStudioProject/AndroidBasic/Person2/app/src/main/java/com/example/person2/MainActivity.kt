package com.example.person2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var person:Person? = null

    //열거형
    enum class PersonType{
        PERSON, STUDENT     // PERSON : 사람, STUDENT : 학생
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createdPersonButton.setOnClickListener {
            makePerson(PersonType.PERSON)
        }

        createdStudentButton.setOnClickListener {
            makePerson(PersonType.STUDENT)
        }
    }

    fun makePerson(type:PersonType){
        val name = input1.text.toString()
        val age = input2.text.toString().toInt()
        val address = input3.text.toString()

        when(type){
            PersonType.PERSON ->{
                person = Person(name,age,address)
                output1.text = "사람 객체 만들어짐 ${person?.name}"
                output2.setImageResource(R.drawable.person)

                person?.walk(output1)
            }
            PersonType.STUDENT->{
                person = Student(name,age,address)
                output1.text = "학생 객체 만들어짐 ${person?.name}"
                output2.setImageResource(R.drawable.student)

                person?.walk(output1)
            }
        }

    }
}