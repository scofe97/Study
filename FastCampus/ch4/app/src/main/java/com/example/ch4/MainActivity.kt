 package com.example.ch4

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.ch4.databinding.ActivityMainBinding
import com.example.ch4.model.History
import org.w3c.dom.Text
import java.lang.NumberFormatException

 class MainActivity : AppCompatActivity() {

     private lateinit var binding : ActivityMainBinding

     private val expressionTextView : TextView by lazy{
         binding.expressionTextView
     }

     private val resultTextView : TextView by lazy{
         binding.resultTextView
     }
     private  val historyLayout : View by lazy{
         binding.historyLayout
     }
     private val historyLinearLayout : LinearLayout by lazy{
         binding.historyLinearLayout
     }
     lateinit var  db : AppDatabase

     private var isOperaator = false

     private var hasOperator = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 컨텍스트, 클래스 이름, 이름
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "historyDB"
        ).build()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun buttonClicked(v: View){
        when(v.id){
            R.id.Button0 -> numberButtonClicked("0")
            R.id.Button1 -> numberButtonClicked("1")
            R.id.Button2 -> numberButtonClicked("2")
            R.id.Button3 -> numberButtonClicked("3")
            R.id.Button4 -> numberButtonClicked("4")
            R.id.Button5 -> numberButtonClicked("5")
            R.id.Button6 -> numberButtonClicked("6")
            R.id.Button7 -> numberButtonClicked("7")
            R.id.Button8 -> numberButtonClicked("8")
            R.id.Button9 -> numberButtonClicked("9")
            R.id.ButtonPlus -> oepratorButtonClicked("+")
            R.id.ButtonMinus -> oepratorButtonClicked("-")
            R.id.ButtonMulti -> oepratorButtonClicked("*")
            R.id.ButtonDivider -> oepratorButtonClicked("/")
            R.id.ButtonModulo -> oepratorButtonClicked("%")


        }
    }

     private fun numberButtonClicked(number:String){

        if(isOperaator){
            expressionTextView.append(" ")
        }

         isOperaator = false

         val expressionText = expressionTextView.text.split(" ")
         if(expressionText.isNotEmpty() && expressionText.last().length>=15){
             // 숫자가 들어온 경우
             Toast.makeText(this, "15자리 까지만 사용할 수 있습니다",Toast.LENGTH_SHORT).show()
             return
         }else if(number == "0" && expressionText.last().isEmpty()){
             Toast.makeText(this, "0은 제일 앞에 올 수 없습니다.",Toast.LENGTH_SHORT).show()
             return
         }

         expressionTextView.append(number)
         resultTextView.text = calculateExpression()
     }

     @RequiresApi(Build.VERSION_CODES.M)
     private fun oepratorButtonClicked(operator : String){

         if(expressionTextView.text.isEmpty()){
             return
         }
         when{
             isOperaator->{
                val text = expressionTextView.text.toString()
                 // 마지막 하나가 사라짐
                 expressionTextView.text = text.dropLast(1)
             }
             hasOperator->{
                 Toast.makeText(this,"연산자는 한 번만 사용할 수 있습니다..", Toast.LENGTH_SHORT).show()
             }
             else ->{
                 expressionTextView.append(" $operator")
             }
         }

         // 글자 마크업 기능
         // 4번째 매개변수는 SPAN이 적용된 좌우측에 문자열이 추가될 때 이 span 값을 적용할 것인지에 대한 설정
         // 현재 설정은 양쪽 제거이다.
         val ssb = SpannableStringBuilder(expressionTextView.text)
         ssb.setSpan(
             ForegroundColorSpan(getColor(R.color.greeen)),
             expressionTextView.text.length-1,
             expressionTextView.text.length,
             Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

         expressionTextView.text = ssb

         isOperaator = true
         hasOperator = true
     }

     fun resultButtonClicked(v: View){
         val expressionTexts = expressionTextView.text.split(" ")

         if(expressionTextView.text.isEmpty() || expressionTexts.size == 1){
             return
         }

         if(expressionTexts.size != 3 && hasOperator){
             Toast.makeText(this,"아직 완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
             return
         }
         if(expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()){
             Toast.makeText(this,"오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
             return
         }

         val expressionText = expressionTextView.text.toString()
         val resultText = calculateExpression()

         Thread(Runnable {
             db.historyDao().insertHistory(History(null, expressionText, resultText))
         }).start()

         resultTextView.text = ""
         expressionTextView.text = resultText

         isOperaator = false
         hasOperator = false
     }

     private fun calculateExpression(): String{
        val expressionTexts = expressionTextView.text.split(" ")

         if(hasOperator.not() || expressionTexts.size != 3){
             return ""
         }else if(expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()){
             return ""
         }

         val exp1 = expressionTexts[0].toBigInteger()
         val exp2 = expressionTexts[2].toBigInteger()
         val op = expressionTexts[1]

         return when(op){
             "+" -> (exp1 + exp2).toString()
             "-"-> (exp1 - exp2).toString()
             "*"-> (exp1 * exp2).toString()
             "/"-> (exp1 / exp2).toString()
             "%"-> (exp1 % exp2).toString()
             else -> ""
         }
     }

     fun clearButtonClicked(v: View){
        expressionTextView.text = ""
         resultTextView.text = ""
         isOperaator = false
         hasOperator = false
     }
     fun historyButtonClicked(v: View){
        historyLayout.isVisible = true
         // 하위 뷰 모두 삭제
         historyLinearLayout.removeAllViews()

         // 디비에서 모든 기록 가져오기
         // 뷰에 모든 기록 할당하기
         Thread(Runnable {
             db.historyDao().getAll().reversed().forEach(){
                // 기존의 뷰가 모두 삭제되어서 만들어줌
                 // 메인스레드가 아니므로 UI 접근이 안딤

                 runOnUiThread{
                     // inflate ( 어디서 인플레이트하냐, 루트(historyLinearLayout 나중에 addView로 붙이므로 null) 이지만 , 어태치루트
                     val historyView =
                         LayoutInflater.from(this).inflate(R.layout.history_row, null, false)
                     historyView.findViewById<TextView>(R.id.expressionTextView).text = it.expression
                     historyView.findViewById<TextView>(R.id.resultTextView).text = "= ${it.result}"

                     historyLinearLayout.addView(historyView)
                 }
             }
         }).start()

     }

     fun closeHistoryButtonClicked(v : View){
        historyLayout.isVisible = false
     }

     fun historyClearButtonClicked(v : View){
         // 뷰에서 모든 기록 삭제
         historyLinearLayout.removeAllViews()
         // 디비에서 모든 기록학제
         Thread(Runnable {
             db.historyDao().deleteAll()
         }).start()
     }

}
 fun String.isNumber() : Boolean{
     return try{
         this.toBigInteger()
         true
     }catch(e: NumberFormatException){
         false
     }
 }