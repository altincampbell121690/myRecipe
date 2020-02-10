package com.example.myrecipe.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.myrecipe.R
import com.example.myrecipe.adapter.IngredientListRyclerViewAdapter
import com.example.myrecipe.services.DataServices
import com.example.myrecipe.utils.PARAMS
import com.example.myrecipe.utils.PARAMS_BY_INGREDIENT_LIST
import com.example.myrecipe.utils.PARAMS_BY_INGREDIENT_NUMBER
import kotlinx.android.synthetic.main.activity_add_to_pantry.*
import okhttp3.Headers

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

            val client = AsyncHttpClient()
            val params = RequestParams()
            //https://api.spoonacular.com/recipes/findByIngredients?apiKey=dcfa3c8d24e24e41989b9848b981b874&ingredients=apples,flour,sugar&number=2
            params.put(PARAMS[PARAMS_BY_INGREDIENT_LIST], "apples,flour,sugar")
            params.put(PARAMS[PARAMS_BY_INGREDIENT_NUMBER], "2")

            client.get("https://api.spoonacular.com/recipes/findByIngredients?apiKey=dcfa3c8d24e24e41989b9848b981b874", params, object: JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                    Log.d("ON NEXT CLICKED", "onSUCCESS")
                    println(json)
                }

                override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                    Log.d("ON NEXT CLICKED", "onFAIL")
                    println("statusCode: $statusCode\nHeaders: $headers\nResponse: $response\n\nTHROWABLE: $throwable")
                }

            })



            println("${myAdapter.selectedList}")
            val selectRecipeIntent = Intent(this, SelectRecipes::class.java)

            startActivity(selectRecipeIntent)
        }
}
