package com.example.myfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    // 화면이 초기화되는 시점에 동작
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.fragment_login, container, false)

        rootView.nextButton.setOnClickListener {
            // 프래그먼트가 액티비티위에 올라오면 그 액티비티를 참조가능
            val mainActivity = activity as MainActivity
            mainActivity.onFragmentChanged(1)
        }
        return rootView
    }

}