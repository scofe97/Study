package com.example.jatlin.extensions

import android.view.View

fun View.dip(dipValue : Float) = context.dip(dipValue)

fun View.toVisible(){
    visibility = View.VISIBLE
}

fun View.toGone(){
    visibility = View.GONE
}