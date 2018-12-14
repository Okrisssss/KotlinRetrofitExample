package com.example.piachimov.firebasekotlinexample

import android.app.Application
import com.example.piachimov.firebasekotlinexample.di.Injector

class AppActivity: Application(){
    override fun onCreate() {
        super.onCreate()
        Injector.initAppComponent(this)
    }
}

