package com.example.piachimov.firebasekotlinexample.ui.function1

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RatingBar
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
        rateButton.setOnClickListener {

            mainActivityPresenter.saveData(editName.text.toString(), ratingBar.rating.toString())
            heroList = mainActivityPresenter.getDataFromfirebaseDatabas()
            recyclerAdapter.showList(heroList)
            heroList.clear()
        }
//        updateButton.setOnClickListener {
//            showRatingDialog(hero)
//        }
    }

    override fun onSuccess(message: String) {
        toast(message)
    }

    fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter
    }


//    fun showRatingDialog(hero: Hero) {
//        val popDialog = AlertDialog.Builder(this)
//        var ratingBar = RatingBar(this)
//        var updateHeroName = EditText(this)
//        ratingBar.rating = hero.rating.toFloat()
//        updateHeroName.setText(hero.name)
//        val view = layoutInflater.inflate(R.layout.update_layout, null)
//        popDialog.setTitle("Update heroes data")
//        popDialog.setView(view)
//        popDialog.setPositiveButton("Update") { dialog, which ->
//            mainActivityPresenter.updateHeroesData(hero,updateHeroName.toString(),ratingBar.toString() )
//
//        }
//        popDialog.setNegativeButton("No") { dialog, which ->
//            dialog.cancel()
//        }
//        popDialog.create()
//        popDialog.show()
//    }

    override fun onDestroy() {
        Injector.releaseMainActivityComponent()
        Injector.mainActivityComponent = null
        super.onDestroy()
    }
}



