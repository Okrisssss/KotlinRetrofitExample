package com.example.piachimov.firebasekotlinexample.di.component

import android.content.Context
import com.example.piachimov.firebasekotlinexample.di.module.AppModule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun context(): Context
    fun firebaseDatabase(): FirebaseDatabase
    fun picasso(): Picasso
    fun firebaseAuth(): FirebaseAuth
    fun firebaseStorage(): FirebaseStorage

}