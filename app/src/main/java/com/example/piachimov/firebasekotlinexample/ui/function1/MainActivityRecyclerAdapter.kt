package com.example.piachimov.firebasekotlinexample.ui.function1

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.Hero
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class MainActivityRecyclerAdapter(var context: Context, var picasso: Picasso, var mainActivityPresenter: MainActivityPresenter) : RecyclerView.Adapter<MainActivityRecyclerAdapter.ViewHolder>() {


    var list = ArrayList<Hero>()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewHeroName = itemView.findViewById<TextView>(R.id.heroName)!!
        val heroRating = itemView.findViewById<RatingBar>(R.id.heroRating)!!
        val rating = itemView.findViewById<TextView>(R.id.rating)!!
        val update = itemView.findViewById<TextView>(R.id.update)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.heroe_item, parent, false)
        return ViewHolder(view)
    }

    fun showList(items: ArrayList<Hero>) {
        this.list = items
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val heroPosition = list[position]
        holder?.textViewHeroName?.text = heroPosition.name
        holder?.heroRating?.rating = heroPosition.rating.toFloat()
        holder?.rating?.text = heroPosition.rating
        holder?.update?.setOnClickListener {
            //showRatingDialog(heroPosition)
            showRatingDialog(heroPosition)
           list= mainActivityPresenter.getDataFromfirebaseDatabas()
            showList(list)
        }
    }



    fun updateHeroesData(hero: Hero, name: String, rating: String) {
        val firebaseDatabas: FirebaseDatabase? = Injector.appComponent?.firebaseDatabase()
        val ref = firebaseDatabas?.getReference("heroes")
        val hero = Hero(hero.id, name, rating)
        ref?.child(hero.id)!!.setValue(hero)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun showRatingDialog(heroPosition: Hero) {
        val popDialog = AlertDialog.Builder(context)
        val ratingBar = RatingBar(context)
        val updateHeroName = EditText(context)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.update_layout, null )
        val updateHeroNameEditText = view.findViewById<EditText>(R.id.updateHeroNameEditText)
        val updateHeroRatingBar = view.findViewById<RatingBar>(R.id.updateHeroRatingBar)
        popDialog.setView(view)
        popDialog.setTitle("Update heroes data")
        updateHeroNameEditText.setText(heroPosition.name)
        updateHeroRatingBar.rating = heroPosition.rating.toFloat()

        popDialog.setPositiveButton("Update") { dialog, which ->
            val dbHero = FirebaseDatabase.getInstance().getReference("heroes")

            val newName = updateHeroNameEditText.text.toString()
            val newRating = updateHeroRatingBar.rating.toString()
            if (newName.isEmpty()){
                updateHeroName.error = "Please Enter the name"
                updateHeroName.requestFocus()
                return@setPositiveButton
            }
            val newHero = Hero(heroPosition.id, newName,newRating)
            dbHero.child(heroPosition.id).setValue(newHero)

            Toast.makeText(context, "Hero updated", Toast.LENGTH_SHORT)




        }
        popDialog.setNegativeButton("No") { dialog, which ->
            dialog.cancel()
        }
        popDialog.create()
        popDialog.show()
    }

}