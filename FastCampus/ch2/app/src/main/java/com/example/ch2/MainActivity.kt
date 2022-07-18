package com.example.ch2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.ch2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var didRun = false

    private val pickNumberSet = hashSetOf<Int>()

    private val numberTextViewList : List<TextView> by lazy{
        listOf<TextView>(
            binding.TextView1,
            binding.TextView2,
            binding.TextView3,
            binding.TextView4,
            binding.TextView5,
            binding.TextView6
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // binding.clearButton
        binding.numberPicker.maxValue = 45
        binding.numberPicker.minValue = 1

        binding.runButton.setOnClickListener{ initRunButton() }
        binding.addButton.setOnClickListener{ initAddButton() }
        binding.clearButton.setOnClickListener{ initClearButton() }

    }

    private fun initRunButton(){
        val list = getRandomNumber()

        didRun = true

        list.forEachIndexed{ index, number ->
            val textView = numberTextViewList[index]

            textView.text = number.toString()
            textView.isVisible = true

            setNumberBackground(number, textView)
        }

        Log.d("Main", list.toString())

    }


    private fun getRandomNumber(): List<Int>{
        val numberList = mutableListOf<Int>()
            .apply{
                for(i in 1..45){
                    if(pickNumberSet.contains(i)){
                        continue
                    }
                    this.add(i)
                }
            }
        numberList.shuffle()

        return (pickNumberSet.toList() + numberList.subList(0,6 - pickNumberSet.size)).sorted()
    }

    private fun initAddButton(){
        if(didRun){
            Toast.makeText(this,"초기화 후에 시도해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if(pickNumberSet.size >= 6){
            Toast.makeText(this,"번호는 6개까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        if(pickNumberSet.contains(binding.numberPicker.value)){
            Toast.makeText(this,"이미 선택한 번호입니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val textView = numberTextViewList[pickNumberSet.size]
        textView.isVisible = true
        textView.text = binding.numberPicker.value.toString()

        setNumberBackground(binding.numberPicker.value, textView)

        pickNumberSet.add(binding.numberPicker.value)
    }

    private fun setNumberBackground(number :Int, textView: TextView){
        when(number){
            in 1..10 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_yellow)
            in 11..20 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_blue)
            in 21..30 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_red)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_gray)
            in 40..45 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_green)
        }
    }


    private fun initClearButton(){
        pickNumberSet.clear()
        numberTextViewList.forEach{
            it.isVisible = false
        }
        didRun = false
    }
}