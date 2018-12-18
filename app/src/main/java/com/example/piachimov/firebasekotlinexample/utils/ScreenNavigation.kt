package com.example.piachimov.firebasekotlinexample.utils

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.app.AppCompatActivity
import com.example.piachimov.firebasekotlinexample.utils.Constants.Companion.REQUEST_GET_SINGLE_FILE


object ScreenNavigation {



    fun switchActivity(context: Context, activity: Class<out AppCompatActivity>) {
        val intent = Intent(context, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }


//    fun sjowFileChooser(context: Context) {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.addCategory(Intent.CATEGORY_OPENABLE)
//        intent.type = "image/*"
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_GET_SINGLE_FILE)
//    }
}