package com.example.ch5_shopping_app.extensions

import android.content.Context
import android.widget.Toast

internal fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}