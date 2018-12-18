package com.example.piachimov.firebasekotlinexample.di.module

import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.login.LoginActivity
import com.example.piachimov.firebasekotlinexample.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule(private val loginActivity: LoginActivity) {


    @ActivityScope
    @Provides
    fun provideLoginPresenter(): LoginPresenter = LoginPresenter(loginActivity, loginActivity.baseContext)

}