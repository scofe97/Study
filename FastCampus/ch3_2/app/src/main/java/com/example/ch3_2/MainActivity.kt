package com.example.ch3_2

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.ch3_2.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewPager: ViewPager2 by lazy {
        binding.viewPager2
    }

    private val progressBar : ProgressBar by lazy{
        binding.progressbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initData()
        initViews()
    }

    private fun initViews() {
        viewPager.setPageTransformer{ page, position ->
            // 가운데가 0 좌측 - 우측 +  진행도중은 float 값 가짐

            when{
                position.absoluteValue >= 1.0F ->{
                    page.alpha = 0F
                }
                position == 0F ->{
                    page.alpha = 1F
                }
                else -> {
                    page.alpha = 1F - 2 * position.absoluteValue
                }
            }
        }
    }

    private fun initData(){
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                // 서버에서 블락하지 않는이상 바로바로 패치가 됨
                minimumFetchIntervalInSeconds = 0
            }
        )

        remoteConfig.fetchAndActivate().addOnCompleteListener{
            // 패치랑 액티비티랑 작업을 완료함

            progressBar.visibility = View.GONE

            if(it.isSuccessful){
                // json 파싱 -> 값 가져오기
                val quotes = parseQuotesJson(remoteConfig.getString("quotes"))
                val isNameRevealed = remoteConfig.getBoolean("is_name_revealed")

                displayQuotesPager(quotes,isNameRevealed)
                viewPager.adapter = QuotePagerAdapter(quotes, isNameRevealed)
            }
        }
    }

    private fun parseQuotesJson(json : String) : List<Quote>{
        val jsonArray = JSONArray(json)
        // JSONArray 는 JSONObject로 이루어짐
        var jsonList = emptyList<JSONObject>()

        for(index in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(index)
            jsonObject?.let{
                // json object가 하나씩 뒤에 붙어지는 형식
                jsonList = jsonList + it
            }
        }

        return jsonList.map{
            Quote(it.getString("quote"), it.getString("name"))
        }
    }

    private fun displayQuotesPager(quotes: List<Quote>, isNameRvealed : Boolean){

        val adapter = QuotePagerAdapter(
            quotes
            ,isNameRvealed
        )
        viewPager.adapter = adapter
        viewPager.setCurrentItem(adapter.itemCount/2, false)
    }


    // 페이지를 스와이프할 때 PageTransformer이 호출됨됨
}