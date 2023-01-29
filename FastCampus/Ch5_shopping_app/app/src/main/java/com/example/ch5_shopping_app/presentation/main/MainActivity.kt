package com.example.ch5_shopping_app.presentation.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.ch5_shopping_app.R
import com.example.ch5_shopping_app.databinding.ActivityMainBinding
import com.example.ch5_shopping_app.presentation.BaseActivity
import com.example.ch5_shopping_app.presentation.BaseFragment
import com.example.ch5_shopping_app.presentation.list.ProductListFragment
import com.example.ch5_shopping_app.presentation.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.koin.android.ext.android.inject

internal class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    override val viewModel : MainViewModel by inject<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding){
        bottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
        showFragment(ProductListFragment(), ProductListFragment.TAG)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_products ->{
                showFragment(ProductListFragment(), ProductListFragment.TAG)
                true
            }

            R.id.menu_profile ->{
                showFragment(ProfileFragment(), ProfileFragment.TAG)
                true
            }

            else -> false
        }
    }

    private fun showFragment(fragment : Fragment, tag : String){
        // findFragmentByTAG 를 사용하여 참조를 가져올 수 있음
        val findFragment = supportFragmentManager.findFragmentByTag(tag)

        // beginTransaction() 를 호출 함으로써 프래그먼트 트랜잭션 작업 시작
        // hide 함수로 프래그먼트를 숨김
        supportFragmentManager.fragments.forEach{ fm ->
            supportFragmentManager.beginTransaction().hide(fm).commit()
        }

        // 만약 태그값으로 프래그먼트값을 찾았으면
        // 해당 프래그먼트를 보여준다.
        // 만약에 없다면? add 함수로 추가해준다.
        findFragment?.let{
            supportFragmentManager.beginTransaction().show(it).commit()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, tag)

                // 해당 프래그먼트의 상태 변화를 최적화해 애니메이션과
                // 트랜잭션이 잘 동작하도록 함함
                .commitAllowingStateLoss()
        }
    }

    override fun observeData()  = viewModel.mainStateLiveData.observe(this){
        when(it){
            is MainState.RefreshOrderList ->{
                binding.bottomNav.selectedItemId = R.id.menu_profile
                val fragment = supportFragmentManager.findFragmentByTag(ProfileFragment.TAG)
                (fragment as? BaseFragment<*,*>)?.viewModel?.fetchData()

            }
        }
    }
}