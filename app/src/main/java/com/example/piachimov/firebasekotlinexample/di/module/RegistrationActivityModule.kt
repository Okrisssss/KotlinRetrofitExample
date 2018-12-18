package com.example.piachimov.firebasekotlinexample.di.module

import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.registration.RegistrationActivity
import com.example.piachimov.firebasekotlinexample.ui.registration.RegistrationPresenter
import dagger.Module
import dagger.Provides

@Module
class RegistrationActivityModule(private val registrationActivity: RegistrationActivity) {

    @ActivityScope
    @Provides
    fun provideRegistrationPresenter(): RegistrationPresenter = RegistrationPresenter(registrationActivity, registrationActivity.baseContext)
}