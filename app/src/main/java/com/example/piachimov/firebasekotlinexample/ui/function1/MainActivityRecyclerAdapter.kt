package com.example.piachimov.firebasekotlinexample.ui.function1

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.model.Hero
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MainActivityRecyclerAdapter(var context: Context, var picasso: Picasso) : RecyclerView.Adapter<MainActivityRecyclerAdapter.ViewHolder>() {

    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter

    var list = ArrayList<Hero>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewHeroName = itemView.findViewById<TextView>(R.id.heroName)
        val heroRating = itemView.findViewById<RatingBar>(R.id.heroRating)
        val rating = itemView.findViewById<TextView>(R.id.rating)
        val update = itemView.findViewById<TextView>(R.id.update)
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
    val hero: Hero = list[position]
    holder?.textViewHeroName?.text = hero.name
    holder?.heroRating?.rating = hero.rating.toFloat()
    holder?.rating?.text = hero.rating
    holder?.update?.setOnClickListener {
        showRatingDialog(hero)
    }
    }


    fun showRatingDialog(hero: Hero) {
        val popDialog = AlertDialog.Builder(context)
        val ratingBar = RatingBar(context)
        val updateHeroName = EditText(context)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.update_layout, null )
        popDialog.setTitle("Update heroes data")
        popDialog.setView(view)
        updateHeroName.setText(hero.name)
        ratingBar.rating = hero.rating.toFloat()

        popDialog.setPositiveButton("Update") { dialog, which ->
            val name = updateHeroName.text.toString()
            val rating = ratingBar.rating.toString()

            if (name.isEmpty()){
                updateHeroName.error = "Please Enter the name"
                updateHeroName.requestFocus()
                return@setPositiveButton
            }
            val heroUpdated = Hero(hero.id, name,rating)
            mainActivityPresenter.updateHeroesData(heroUpdated,updateHeroName.toString(),ratingBar.toString() )

        }
        popDialog.setNegativeButton("No") { dialog, which ->
            dialog.cancel()
        }
        popDialog.create()
        popDialog.show()
    }


    override fun getItemCount(): Int {
        return list.size
    }

}