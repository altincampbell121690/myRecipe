package com.example.myrecipe.controller
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.myrecipe.R
import com.example.myrecipe.adapter.RecipeCardAdapter
import com.example.myrecipe.model.RecipeDetail
import com.example.myrecipe.model.RecipeFromIngredient
import com.example.myrecipe.services.SpoonacularClient
import com.example.myrecipe.utils.EXTRA_RECIPE_DETAIL
import com.example.myrecipe.utils.EXTRA_RECIPE_LIST
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_select_recipe.*
import okhttp3.Headers
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
                SpoonacularClient.getRecipeInformation(it.id.toString(), handler = object:
                    JsonHttpResponseHandler() {
                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                        val gson= Gson()
                       // Log.d("ON RECIPE CLICKED", "onSUCCESS")
                        if(json != null){
                            val recipe =  gson.fromJson(json.jsonObject.toString(), RecipeDetail::class.java)
                            val recipeDetailIntent = Intent(applicationContext,RecipeDetails::class.java)
                            recipeDetailIntent.putExtra(EXTRA_RECIPE_DETAIL, recipe)
                            Log.d("ON RECIPE CLICKED", "onSUCCESS\n\n${recipe.title}\n${recipe.image}")
                            startActivity(recipeDetailIntent)
                        }


                    }

                    override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                        Log.d("ON RECIPE CLICKED", "onFAIL")
                        println("statusCode: $statusCode\nHeaders: $headers\nResponse: $response\n\nTHROWABLE: $throwable")
                    }

                })
            }
            rvRecipeCardHolder.adapter = myAdapter
            rvRecipeCardHolder.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }else{
            println("RECIPE LIST as JSONarray is EMPTY")
        }



        //println("${obj.title}\n${obj.image}")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        //menuInflater.inflate(R.menu.recipes_bar_menu, menu)
        return true
    }


}