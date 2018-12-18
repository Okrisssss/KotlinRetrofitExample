package com.example.piachimov.firebasekotlinexample.di.component

import com.example.piachimov.firebasekotlinexample.di.module.LoginActivityModule
import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.login.LoginActivity
import dagger.Component


@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [LoginActivityModule::class])
interface LoginActivityComponent {
    fun inject(loginActivity: LoginActivity)
}