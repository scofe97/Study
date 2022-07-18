package com.example.chapter5_clean_arch.presentation.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter5_clean_arch.R
import com.example.chapter5_clean_arch.databinding.ActivityListBinding
import com.example.chapter5_clean_arch.presentation.BaseActivity
import com.example.chapter5_clean_arch.presentation.detail.DetailActivity
import com.example.chapter5_clean_arch.presentation.detail.DetailMode
import com.example.chapter5_clean_arch.presentation.list.ListViewModel
import com.example.chapter5_clean_arch.presentation.list.ToDoListState
import com.example.chapter5_clean_arch.presentation.view.ToDoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

internal class ListActivity : BaseActivity<ListViewModel>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    private lateinit var binding : ActivityListBinding

    private val adapter = ToDoAdapter()

    override val viewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initViews(binding : ActivityListBinding) = with(binding){
        // 리사이클러뷰 설정 ( 수직, 어댑터 )
        recyclerView.layoutManager = LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        // 새로고침 ( 데이터 최신화 )
        refreshLayout.setOnRefreshListener {
            viewModel.fetchData()
        }

        addToDoButton.setOnClickListener {
            startActivityForResult(
                DetailActivity.getIntent(this@ListActivity, DetailMode.WRITE),
                DetailActivity.FETCH_REQUEST_CODE
            )
        }
    }

    override fun observerData() {
        viewModel.todoListLiveData.observe(this){
            when(it){
                is ToDoListState.UnInitialized ->{
                    initViews(binding)
                }
                is ToDoListState.Loading ->{
                    handleLoadingState()
                }
                is ToDoListState.Success ->{
                    handleSuccessState(it)
                }
                is ToDoListState.Error ->{
                    handleErrorState()
                }
            }
        }
    }

    private fun handleLoadingState() = with(binding) {
        refreshLayout.isRefreshing = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == DetailActivity.FETCH_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            viewModel.fetchData()
        }
    }

    private fun handleSuccessState(state: ToDoListState.Success) = with(binding) {
        refreshLayout.isEnabled = state.toDoList.isNotEmpty()
        refreshLayout.isRefreshing = false

        if (state.toDoList.isEmpty()) {
            emptyResultTextView.isGone = false
            recyclerView.isGone = true
        } else {
            emptyResultTextView.isGone = true
            recyclerView.isGone = false
            adapter.setToDoList(
                state.toDoList,
                toDoItemClickListener = {
                    startActivityForResult(
                        DetailActivity.getIntent(this@ListActivity, it.id, DetailMode.DETAIL),
                        DetailActivity.FETCH_REQUEST_CODE
                    )
                }, toDoCheckListener = {
                    viewModel.updateEntity(it)
                }
            )
        }
    }

    private fun handleErrorState() {
        Toast.makeText(this, "에러가 발생했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_all -> {
                viewModel.deleteAll()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)

        return true
    }
}