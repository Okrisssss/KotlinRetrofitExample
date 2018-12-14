package com.example.piachimov.firebasekotlinexample.ui.function1

import android.content.Context
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.Hero
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivityPresenter(var mainActivityView: MainActivityView, var context: Context) {

    private val firebaseDatabas: FirebaseDatabase? = Injector.appComponent?.firebaseDatabase()
    private val ref = firebaseDatabas?.getReference("heroes")
    var heroList: ArrayList<Hero> = arrayListOf()

    fun saveData(name: String, rating: String) {
        val heroId = ref?.push()?.key
        val hero = Hero(heroId!!, name, rating)
        ref?.child(heroId)?.setValue(hero)

        mainActivityView.onSuccess("Success message")
    }

    fun getDataFromfirebaseDatabas(): ArrayList<Hero> {
        ref?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    for (h in p0.children) {
                        val heroItem = h.getValue(Hero::class.java)
                        heroList.add(heroItem!!)
                    }
                }
            }

        })
        return heroList
    }

    fun updateHeroesData(hero: Hero,name: String, rating: String) {
       val hero = Hero(hero.id, name, rating)
       ref?.child(hero.id)!!.setValue(hero)
        mainActivityView.onSuccess("Hero was updated")

    }


    fun setUpMainActivityView(mainActivityView: MainActivityView) {
        this.mainActivityView = mainActivityView
    }

}