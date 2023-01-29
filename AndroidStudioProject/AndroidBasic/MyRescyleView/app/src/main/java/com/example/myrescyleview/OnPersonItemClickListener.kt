package com.example.myrescyleview

import android.view.View

interface OnPersonItemClickListener {

    fun onItemClick(holder:PersonAdapter.ViewHolder?, view: View?, position:Int){

    }
}