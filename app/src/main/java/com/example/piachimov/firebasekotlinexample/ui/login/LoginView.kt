package com.example.piachimov.firebasekotlinexample.ui.login

interface LoginView {

    fun onLoginSuccess(message: String)

    fun onLoginFailed(message: String)
}