package com.example.piachimov.firebasekotlinexample.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.Hero
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {


    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter
    @Inject
    lateinit var recyclerAdapter: MainActivityRecyclerAdapter

    lateinit var heroList: ArrayList<Hero>
    var hero: Hero = Hero()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Injector.initMainActivityComponent(this)
        Injector.mainActivityComponent?.inject(this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        initRecyclerView()


    }


    fun onClickRateButton(view: View){
        mainActivityPresenter.saveData(editName.text.toString(), ratingBar.rating.toString())
        heroList = mainActivityPresenter.getDataFromfirebaseDatabas()
        recyclerAdapter.showList(heroList)
    }


    override fun onSuccess(message: String) {
        toast(message)
    }

    fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter
    }

    override fun onListUpdated(list: ArrayList<Hero>) {
        recyclerAdapter.showList(list)
    }

    override fun onItemDeleted(list: ArrayList<Hero>) {
        recyclerAdapter.showList(list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.menu_logout){
            mainActivityPresenter.logOut()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        Injector.releaseMainActivityComponent()
        Injector.mainActivityComponent = null
        super.onDestroy()
    }
}



