package com.example.piachimov.firebasekotlinexample.di

import com.example.piachimov.firebasekotlinexample.AppActivity
import com.example.piachimov.firebasekotlinexample.di.component.AppComponent
import com.example.piachimov.firebasekotlinexample.di.component.DaggerAppComponent
import com.example.piachimov.firebasekotlinexample.di.component.DaggerMainActivityComponent
import com.example.piachimov.firebasekotlinexample.di.component.MainActivityComponent
import com.example.piachimov.firebasekotlinexample.di.module.AppModule
import com.example.piachimov.firebasekotlinexample.di.module.MainActivityModule
import com.example.piachimov.firebasekotlinexample.ui.function1.MainActivity
import com.squareup.picasso.Picasso

object Injector {

    var appComponent: AppComponent? = null
    var mainActivityComponent: MainActivityComponent? = null

    fun initAppComponent(appActivity: AppActivity) {
        appComponent = appComponent ?: DaggerAppComponent.builder()
                .appModule(AppModule(appActivity))
                .build()
    }

    fun  initMainActivityComponent(mainActivity: MainActivity){
        mainActivityComponent = mainActivityComponent ?: DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(MainActivityModule(mainActivity))
                .build()
    }


    fun releaseMainActivityComponent(){
        mainActivityComponent = null
    }
}