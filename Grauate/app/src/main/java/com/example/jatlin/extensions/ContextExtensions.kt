package com.example.jatlin.extensions

import android.content.Context
import android.widget.Toast

internal fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

internal fun Context.dip(dipValue : Float) = (dipValue * resources.displayMetrics.density).toInt()