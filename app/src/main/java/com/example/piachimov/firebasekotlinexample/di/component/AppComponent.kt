package com.example.piachimov.firebasekotlinexample.di.component

import android.content.Context
import com.example.piachimov.firebasekotlinexample.di.module.AppModule
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun context(): Context
    fun firebaseDatabase(): FirebaseDatabase
    fun picasso(): Picasso

}