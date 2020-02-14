package com.example.myrecipe.controller

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.myrecipe.R
import com.example.myrecipe.adapter.IngredientListRyclerViewAdapter
import com.example.myrecipe.model.RecipeFromIngredient
import com.example.myrecipe.model.RecipeList
import com.example.myrecipe.services.DataServices
import com.example.myrecipe.services.SpoonacularClient
import com.example.myrecipe.utils.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_to_pantry.*
import okhttp3.Headers
import java.util.ArrayList

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

            var listofIng = listOf("apples","flour","sugar")
            val client = AsyncHttpClient()
            val params = RequestParams()
            //https://api.spoonacular.com/recipes/findByIngredients?apiKey=dcfa3c8d24e24e41989b9848b981b874&ingredients=apples,flour,sugar&number=2
          /*  params.put(PARAMS[PARAMS_BY_INGREDIENT_LIST], "apples,flour,sugar")
            params.put(PARAMS[PARAMS_BY_INGREDIENT_NUMBER], "2")*/
            //params.put(PARAMS_BY_INGREDIENT_LIST, "apples,flour,sugar")
            params.put(PARAMS_BY_INGREDIENT_LIST, listofIng.toString())
            params.put("number", 2)

           /* client.get("$API_URL$GET_RECIPES_BY_INGREDIENT_PATH$API_KEY", params, object: JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                    Log.d("ON NEXT CLICKED", "onSUCCESS")
                    var gson = Gson()
                    println(json)
                    var test = gson.fromJson(json!!.jsonArray[0]!!.toString(), RecipeFromIngredient::class.java)
                    println(test.title)
                }

                override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                    Log.d("ON NEXT CLICKED", "onFAIL")
                    println("statusCode: $statusCode\nHeaders: $headers\nResponse: $response\n\nTHROWABLE: $throwable")
                }

            })*/
            SpoonacularClient.searchRecipeByIngredient(listofIng, handler = object: JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                    Log.d("ON NEXT CLICKED", "onSUCCESS")
                    require(json != null){
                        Toast.makeText(applicationContext, "ERROR: JSON EMPTY", Toast.LENGTH_LONG).show()
                    }
                       //val recipeArray =  RecipeList(DataServices.fromJsonArray(json.jsonArray))
                    val recipeArray = DataServices.fromJsonArray(json.jsonArray)
                    println("${myAdapter.selectedList}")
                    val selectRecipeIntent = Intent(applicationContext, SelectRecipes::class.java)

                   // selectRecipeIntent.putExtra(EXTRA_RECIPE_LIST, recipeArray)
                    selectRecipeIntent.putParcelableArrayListExtra(EXTRA_RECIPE_LIST, recipeArray)
                    startActivity(selectRecipeIntent)

                }

                override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                    Log.d("ON NEXT CLICKED", "onFAIL")
                    println("statusCode: $statusCode\nHeaders: $headers\nResponse: $response\n\nTHROWABLE: $throwable")

                }

            })

        }
}
