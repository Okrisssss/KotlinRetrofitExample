package com.example.piachimov.firebasekotlinexample.ui.function1

import com.example.piachimov.firebasekotlinexample.model.Hero

interface MainActivityView {

    fun onSuccess(message: String)

    fun onListUpdated(list: ArrayList<Hero>)
}