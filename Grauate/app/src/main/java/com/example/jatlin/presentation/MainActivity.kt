package com.example.jatlin.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.jatlin.R
import com.example.jatlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    // 뒤로가기
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun initViews() {
        // 네비게이션 연결
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavigationHostContainer) as NavHostFragment
        navController = navHostFragment.navController
        // 바텀 네비게이션 연결
        binding.bottomNavigationView.setupWithNavController(navController)

        // 앱 바 생성
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.productFragment, R.id.chatFragment, R.id.profileFragment)
        )
        // 툴바가 있으므로 툴바에 설정해줌
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        // 페이지별 툴바, 앱바 보이는여부
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.productCreateFragment -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.bottomNavigationView.visibility = View.GONE
                }
                R.id.chatDetailFragment,
                R.id.productDetailFragment,
                R.id.loginFragment->{
                    binding.toolbar.visibility = View.GONE
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    binding.toolbar.visibility = View.VISIBLE
                }
            }
        }
    }

}