package com.example.piachimov.firebasekotlinexample.di.module

import android.content.Context
import com.example.piachimov.firebasekotlinexample.AppActivity
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val appActivity: AppActivity) {


    @Singleton
    @Provides
    fun provideContext(): Context = appActivity

    @Singleton
    @Provides
    fun provideFirebase(): FirebaseDatabase = FirebaseDatabase.getInstance()


    @Singleton
    @Provides
    fun providePicasso(context: Context): Picasso = Picasso.with(context)
}