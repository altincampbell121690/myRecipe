package com.example.myrecipe.controller
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.myrecipe.R

class SelectRecipes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_recipe)
        actionBar?.title = "Select"
        supportActionBar?.setTitle("Take Back Your Kitchen");
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        //menuInflater.inflate(R.menu.recipes_bar_menu, menu)
        return true
    }
}