package com.example.coroutinechallenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        CoroutineScope(Dispatchers.Default).launch{
            Log.i("MyTag", "Downloading user  in ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.Main).launch{
            Log.i("MyTag", "Downloading user  in ${Thread.currentThread().name}")
        }
    }
}

