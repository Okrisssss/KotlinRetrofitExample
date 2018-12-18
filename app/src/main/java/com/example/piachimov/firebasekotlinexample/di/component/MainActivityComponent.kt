package com.example.piachimov.firebasekotlinexample.di.component

import com.example.piachimov.firebasekotlinexample.di.module.MainActivityModule
import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}