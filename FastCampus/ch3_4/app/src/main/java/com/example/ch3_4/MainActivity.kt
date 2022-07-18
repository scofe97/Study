package com.example.ch3_4

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.ch3_4.adapter.BookAdapter
import com.example.ch3_4.adapter.HistoryAdapter
import com.example.ch3_4.api.BookService
import com.example.ch3_4.databinding.ActivityMainBinding
import com.example.ch3_4.model.BestSellerDTO
import com.example.ch3_4.model.History
import com.example.ch3_4.model.SearchBookDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : BookAdapter
    private lateinit var historyAdapter : HistoryAdapter
    private lateinit var bookService : BookService
    private lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initBookRecyclerView()
        initHistoryRecyclerView()

        db = getAppDatabase(this)

        setContentView(binding.root)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks(getString(R.string.interParkAPIKey))
            .enqueue(object: Callback<BestSellerDTO>{
                override fun onResponse(call: Call<BestSellerDTO>, response: Response<BestSellerDTO>) {
                    // 응답에 성공

                    if(response.isSuccessful.not()){
                        Log.e(TAG,"NOT!! SUCCESS")
                        return
                    }

                    response.body()?.let{
                        Log.d(TAG, it.toString())

                        it.books.forEach{ book ->
                            Log.d(TAG, book.toString())
                        }

                        adapter.submitList(it.books)
                    }


                }
                override fun onFailure(call: Call<BestSellerDTO>, t: Throwable) {
                    // 응답에 실패

                    Log.e(TAG,t.toString())
                }
            })

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initSearchEditText(){
        binding.searchEditText.setOnKeyListener{ v, keyCode, event ->
            // 입력된 키가 엔터이고, 모션이 다운일 때
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN){
                search(binding.searchEditText.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        binding.searchEditText.setOnTouchListener{v, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                showHistoryView()
            }
            return@setOnTouchListener false
        }
    }

    private fun search(keyword : String){
        bookService.getBookByName(getString(R.string.interParkAPIKey), keyword)
            .enqueue(object: Callback<SearchBookDTO>{
            override fun onResponse(call: Call<SearchBookDTO>, response: Response<SearchBookDTO>) {
                // 응답에 성공

                // 키워드 저장
                hideHistoryView()
                saveSearchKeyword(keyword)
                binding.searchEditText.setText(keyword)

                if(response.isSuccessful.not()){
                    Log.e(TAG,"NOT!! SUCCESS")
                    return
                }

                adapter.submitList(response.body()?.books.orEmpty())

            }
            override fun onFailure(call: Call<SearchBookDTO>, t: Throwable) {

                hideHistoryView()
            }
        })
    }


    private fun initBookRecyclerView(){
        adapter = BookAdapter ( clickListener = {
            val intent = Intent(this, DetailActivity::class.java)
            // 클래스 자체를 보내기위해 데이터 클래스를 직렬화 시켜주어야함
            intent.putExtra("bookModel", it)
            startActivity(intent)
        })

        // 리사잌르러뷰의 형태를 결정
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    private fun initHistoryRecyclerView(){
        historyAdapter = HistoryAdapter (
            historyDeleteClickedListener = { keyword:String -> deleteSearchKeyword(keyword) },
            search = { keyword:String -> search(keyword)}
        )

        binding.historyRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.historyRecyclerView.adapter = historyAdapter
        initSearchEditText()
    }

    private fun showHistoryView(){

        Thread{
            val keywords = db.historyDao().getAll().reversed()

            runOnUiThread{
                binding.historyRecyclerView.isVisible = true
                historyAdapter.submitList(keywords.orEmpty())
            }
        }.start()

        binding.historyRecyclerView.isVisible = true
    }

    private fun hideHistoryView(){
        binding.historyRecyclerView.isVisible = false
    }

    private fun saveSearchKeyword(keyword : String){
        Thread(Runnable{
            db.historyDao().insertHistory(History(null,keyword))
        }).start()
    }

    private fun deleteSearchKeyword(keyword : String){
        Thread(Runnable{
            db.historyDao().delete(keyword)
            showHistoryView()
            // todo 뷰 갱신
        }).start()
    }

    companion object{
        private const val TAG = "MainActivity"
    }
}