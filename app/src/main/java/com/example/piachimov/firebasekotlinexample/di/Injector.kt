package com.example.piachimov.firebasekotlinexample.di

import com.example.piachimov.firebasekotlinexample.AppActivity
import com.example.piachimov.firebasekotlinexample.di.component.*
import com.example.piachimov.firebasekotlinexample.di.module.AppModule
import com.example.piachimov.firebasekotlinexample.di.module.LoginActivityModule

import com.example.piachimov.firebasekotlinexample.di.module.MainActivityModule
import com.example.piachimov.firebasekotlinexample.di.module.RegistrationActivityModule
import com.example.piachimov.firebasekotlinexample.ui.login.LoginActivity
import com.example.piachimov.firebasekotlinexample.ui.main.MainActivity
import com.example.piachimov.firebasekotlinexample.ui.registration.RegistrationActivity

object Injector {

    var appComponent: AppComponent? = null
    var mainActivityComponent: MainActivityComponent? = null
    var loginActivityComponent: LoginActivityComponent? = null
    var registrationActivityComponent: RegistrationActivityComponent? = null

    fun initAppComponent(appActivity: AppActivity) {
        appComponent = appComponent ?: DaggerAppComponent.builder()
                .appModule(AppModule(appActivity))
                .build()
    }

    fun initMainActivityComponent(mainActivity: MainActivity) {
        mainActivityComponent = mainActivityComponent ?: DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(MainActivityModule(mainActivity))
                .build()
    }

    fun initLoginActivityComponent(loginActivity: LoginActivity){
        loginActivityComponent = loginActivityComponent ?: DaggerLoginActivityComponent.builder()
                .appComponent(appComponent)
                .loginActivityModule(LoginActivityModule(loginActivity))
                .build()
    }

    fun initRegistrationActivityComponent(registrationActivity: RegistrationActivity){
        registrationActivityComponent = registrationActivityComponent ?: DaggerRegistrationActivityComponent.builder()
                .appComponent(appComponent)
                .registrationActivityModule(RegistrationActivityModule(registrationActivity))
                .build()
    }


    fun releaseMainActivityComponent() {
        mainActivityComponent = null
    }

    fun releaseLoginActivityComponent(){
        loginActivityComponent = null
    }

    fun releaseRegistrationActivityComponent(){
        registrationActivityComponent = null
    }
}