package com.example.myrecipe.controller
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.myrecipe.R

class SelectRecipes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_recipe)
        val actionBar = supportActionBar
        actionBar?.title = "Select"
        actionBar?.setTitle("Take Back Your Kitchen");
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.drawable.ic_logo_color)
        actionBar.setDisplayUseLogoEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        //menuInflater.inflate(R.menu.recipes_bar_menu, menu)
        return true
    }
}