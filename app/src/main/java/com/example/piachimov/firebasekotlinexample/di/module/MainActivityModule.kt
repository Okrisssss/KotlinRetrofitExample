package com.example.piachimov.firebasekotlinexample.di.module

import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.main.MainActivityPresenter
import com.example.piachimov.firebasekotlinexample.ui.main.MainActivity
import com.example.piachimov.firebasekotlinexample.ui.main.MainActivityRecyclerAdapter
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private  val mainActivity: MainActivity) {



    @ActivityScope
    @Provides
    fun provideFunctionOnePresenter(): MainActivityPresenter = MainActivityPresenter(mainActivity, mainActivity.baseContext)

    @ActivityScope
    @Provides
    fun provideMainActivityRecyclerAdapter(picasso: Picasso, mainActivityPresenter: MainActivityPresenter): MainActivityRecyclerAdapter = MainActivityRecyclerAdapter(mainActivity, picasso, mainActivityPresenter)
}