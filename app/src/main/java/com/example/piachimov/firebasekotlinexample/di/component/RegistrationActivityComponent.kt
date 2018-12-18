package com.example.piachimov.firebasekotlinexample.di.component

import com.example.piachimov.firebasekotlinexample.di.module.RegistrationActivityModule
import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.registration.RegistrationActivity
import dagger.Component


@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [RegistrationActivityModule::class])
interface RegistrationActivityComponent {
    fun inject(registrationActivity: RegistrationActivity)
}