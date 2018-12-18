package com.example.piachimov.firebasekotlinexample.ui.main

import android.content.Context
import android.os.Build
import android.support.v4.content.PermissionChecker.checkSelfPermission
import com.example.piachimov.firebasekotlinexample.Manifest
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.Hero
import com.example.piachimov.firebasekotlinexample.ui.login.LoginActivity
import com.example.piachimov.firebasekotlinexample.utils.ScreenNavigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivityPresenter(var mainActivityView: MainActivityView, var context: Context) {

    private val mAuth: FirebaseAuth? = Injector.appComponent?.firebaseAuth()
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

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot!!.exists()) {
                    heroList.clear()
                    for (hero in dataSnapshot.children) {
                        val heroItem = hero.getValue(Hero::class.java)
                        heroList.add(heroItem!!)
                    }
                    mainActivityView.onListUpdated(heroList)
                }
            }
        })
        return heroList
    }

    fun deleteHeroItem(hero: Hero) {
        ref?.child(hero.id)?.removeValue()
        mainActivityView.onItemDeleted(heroList)
        heroList.clear()
    }

    fun logOut(){
        mAuth?.signOut()
        ScreenNavigation.switchActivity(context, LoginActivity::class.java)
    }


    fun checkPermission(){
    }

    fun setUpMainActivityView(mainActivityView: MainActivityView) {
        this.mainActivityView = mainActivityView
    }


}