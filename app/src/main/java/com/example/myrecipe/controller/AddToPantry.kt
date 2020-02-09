package com.example.myrecipe.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipe.R
import com.example.myrecipe.adapter.IngredientListRyclerViewAdapter
import com.example.myrecipe.model.IngredientTest
import com.example.myrecipe.services.DataServices
import kotlinx.android.synthetic.main.activity_add_to_pantry.*
import kotlinx.android.synthetic.main.ingredient_list_item.*
import kotlinx.android.synthetic.main.ingredient_list_item.view.*

class AddToPantry : AppCompatActivity() {
    lateinit var myAdapter : IngredientListRyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_pantry)
        var actionBar = supportActionBar
        actionBar?.title = "Ingredients"
        actionBar?.setTitle("Find Ingredients")

        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setLogo(R.drawable.ic_logo_color)
        actionBar!!.setDisplayUseLogoEnabled(true)
        myAdapter  = IngredientListRyclerViewAdapter(this,
            DataServices.ingredientsList
        )
        rvIngredientList.adapter = myAdapter
        rvIngredientList.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // set our own menu to that menu
        menuInflater.inflate(R.menu.search_bar_menu, menu) // activate the options menu

        // add a listener
        val searchItem  = menu!!.findItem(R.id.action_search)// get refrence to searchview
        val searchVIew: SearchView = searchItem.actionView as SearchView
// copied from youtube not sure how it works
        searchVIew.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                myAdapter.filter.filter(newText)
                return false
            }

        })

        return true

    }


        fun onNextClicked(view:View){
            myAdapter.selectedList.forEach { item ->
                println("\nITEM NAME: ${item.name}")
            }

            println("${myAdapter.selectedList}")
            val selectRecipeIntent = Intent(this, SelectRecipes::class.java)

            startActivity(selectRecipeIntent)
        }
}
