package com.example.piachimov.firebasekotlinexample.model

class Hero (val id: String, val name: String, val rating: String){
    constructor() : this("", "", "")
}