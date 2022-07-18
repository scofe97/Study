package com.example.ch4_3

import android.app.Activity
import android.content.ContentProvider
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch4_3.MapActivity.Companion.SEARCH_RESULT_EXTRA_KEY
import com.example.ch4_3.databinding.ActivityMainBinding
import com.example.ch4_3.model.LocationLatLngEntity
import com.example.ch4_3.model.SearchResultEntity
import com.example.ch4_3.response.search.Poi
import com.example.ch4_3.response.search.Pois
import com.example.ch4_3.utillity.RetrofitUtil
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : SearchRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job()

        initAdapter()
        initView()
        bindViews()
        initData()
    }

    private fun initView() = with(binding){
        emptyResultTextView.isVisible = false
        recyclerView.adapter = adapter
        // recyclerView.layoutManager = LinearLayoutManager(binding.root.context)

    }

    private fun bindViews() = with(binding){
        searchButton.setOnClickListener{
            searchKeyword(searchBarInputView.text.toString())
        }
    }

    private fun initAdapter(){
        adapter = SearchRecyclerView()
    }
    private fun initData(){
        adapter.notifyDataSetChanged()
    }

    private fun setData(pois : Pois){
        val dataList = pois.poi.map{
           SearchResultEntity(
               name = it.name ?: "빌딩명 없음",
               fullAddress = makeMainAddress(it),
               locationLatLng = LocationLatLngEntity(
                   it.noorLat,
                   it.noorLon
               )
           )
        }
        adapter.setSearchResultList(dataList){
            Toast.makeText(this,"빌딩이름 : ${it.name}  주소 : ${it.fullAddress}  위도/경도 : ${it.locationLatLng}",Toast.LENGTH_SHORT).show()
            startActivity(
                Intent(this, MapActivity::class.java).apply{
                    putExtra(SEARCH_RESULT_EXTRA_KEY, it)
                }
            )
        }
    }

    private fun searchKeyword(keywordString : String){
        launch(coroutineContext) {
            try{
                withContext(Dispatchers.IO){
                    val response = RetrofitUtil.apiService.getSearchLocation(
                        keyword = keywordString
                    )
                    if(response.isSuccessful){
                        val body = response.body()
                        withContext(Dispatchers.Main){
                            Log.d("response",body.toString())
                            body?.let{ searchResponse ->
                                setData(searchResponse.searchPoiInfo.pois)
                            }
                        }
                    }
                }

            }catch (e : Exception){
                Toast.makeText(this@MainActivity,"에러발생 : ${e.message}",Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    private fun makeMainAddress(poi: Poi): String =
        if (poi.secondNo?.trim().isNullOrEmpty()) {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrName?.trim() ?: "") + " " +
                    poi.firstNo?.trim()
        } else {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrName?.trim() ?: "") + " " +
                    (poi.firstNo?.trim() ?: "") + " " +
                    poi.secondNo?.trim()
        }
}