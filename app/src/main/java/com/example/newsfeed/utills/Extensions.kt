package com.example.newsfeed.utills

import android.content.Context
import android.widget.Toast

fun makeToast(context: Context, massage: String) {
    Toast.makeText(context, massage, Toast.LENGTH_SHORT).show()
}