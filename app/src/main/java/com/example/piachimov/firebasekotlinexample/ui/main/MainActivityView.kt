package com.example.piachimov.firebasekotlinexample.ui.main

import com.example.piachimov.firebasekotlinexample.model.Hero

interface MainActivityView {

    fun onSuccess(message: String)

    fun onListUpdated(list: ArrayList<Hero>)

    fun onItemDeleted(list: ArrayList<Hero>)
}