package com.example.myrecipe.controller
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myrecipe.R
import com.example.myrecipe.adapter.RecipeCardAdapter
import com.example.myrecipe.model.RecipeFromIngredient
import com.example.myrecipe.model.RecipeList
import com.example.myrecipe.utils.EXTRA_RECIPE_LIST
import kotlinx.android.synthetic.main.activity_select_recipe.*
import java.util.ArrayList

class SelectRecipes : AppCompatActivity() {
    lateinit var myAdapter : RecipeCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_recipe)
        val actionBar = supportActionBar
        actionBar?.title = "Select"
        actionBar?.setTitle("Take Back Your Kitchen");
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.drawable.ic_logo_color)
        actionBar.setDisplayUseLogoEnabled(true)
        val recipes: ArrayList<RecipeFromIngredient>?  = intent.getParcelableArrayListExtra(EXTRA_RECIPE_LIST)
        if (recipes != null){
            val obj:RecipeFromIngredient = recipes[0]
            println("${obj.title}\n${obj.image}")
            myAdapter = RecipeCardAdapter(this, recipes!!){
                Toast.makeText(this,"IM CLICKED", Toast.LENGTH_LONG).show()
            }
            rvRecipeCardHolder.adapter = myAdapter
            rvRecipeCardHolder.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }else{
            println("FAILLLLLLLLL")
        }



        //println("${obj.title}\n${obj.image}")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        //menuInflater.inflate(R.menu.recipes_bar_menu, menu)
        return true
    }


}