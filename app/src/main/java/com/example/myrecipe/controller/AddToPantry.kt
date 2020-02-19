package com.example.myrecipe.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.myrecipe.R
import com.example.myrecipe.adapter.IngredientListRyclerViewAdapter
import com.example.myrecipe.services.DataServices
import com.example.myrecipe.services.SpoonacularClient
import com.example.myrecipe.utils.*
import com.example.myrecipe.view.FilterDialog
import kotlinx.android.synthetic.main.activity_add_to_pantry.*
import kotlinx.android.synthetic.main.fab_layout.*
import okhttp3.Headers

class AddToPantry : AppCompatActivity(), FilterDialog.FilterDialogLister {
    lateinit var fabOpen: Animation
    lateinit var fabClose: Animation
    lateinit var fabClock: Animation
    lateinit var fabAntiClock: Animation
    lateinit var myAdapter: IngredientListRyclerViewAdapter
    lateinit var selectedDiet:String
   lateinit var intoleranceArr : MutableList<String>

    var isOpen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_pantry)
        var actionBar = supportActionBar
        actionBar?.title = "Ingredients"
        actionBar?.setTitle("Find Ingredients")
        selectedDiet = ""

        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setLogo(R.drawable.ic_logo_color)
        actionBar!!.setDisplayUseLogoEnabled(true)
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        fabAntiClock = AnimationUtils.loadAnimation(this, R.anim.fab_rotate_anitclock)
        fabClock = AnimationUtils.loadAnimation(this, R.anim.fab_rotate_clock)
        myAdapter = IngredientListRyclerViewAdapter(
            this,
            DataServices.ingredientListFull
        )
        rvIngredientList.adapter = myAdapter
        rvIngredientList.layoutManager = LinearLayoutManager(this)
        intoleranceArr = mutableListOf()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // set our own menu to that menu
        menuInflater.inflate(R.menu.search_bar_menu, menu) // activate the options menu

        // add a listener
        val searchItem = menu!!.findItem(R.id.action_search)// get refrence to searchview
        val searchVIew: SearchView = searchItem.actionView as SearchView
// copied from youtube not sure how it works
        searchVIew.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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


    fun onMenuClicked(view: View) {
        if (isOpen) {
            tvFilterText.visibility = View.INVISIBLE
            tvNext.visibility = View.INVISIBLE
            fab2.startAnimation(fabClose)
            fab1.startAnimation(fabClose)
            fab.startAnimation(fabAntiClock)
            fab2.isClickable = false
            fab1.isClickable = false
            isOpen = false
        } else {
            tvFilterText.visibility = View.VISIBLE
            tvNext.visibility = View.VISIBLE
            fab2.startAnimation(fabOpen)
            fab1.startAnimation(fabOpen)
            fab.startAnimation(fabClock)
            fab2.isClickable = true
            fab1.isClickable = true
            isOpen = true
        }
    }

    fun onFilterClicked(view: View) {
        openModal()
    }

    private fun openModal(){
        val modal = FilterDialog()
        modal.show(supportFragmentManager,"MODAL")
    }

    fun onNextClicked(view: View) {

        myAdapter.selectedList.forEach { item ->
            println("\nITEM NAME: ${item.name}")
        }
        val listOfIng = DataServices.toIngredientList(myAdapter.selectedList)


        SpoonacularClient.complexSearch(
            listOfIng,
            diet = selectedDiet,
            listOfIntolerances = intoleranceArr,
            handler = object : JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                    Log.d("ON NEXT CLICKED", "onSUCCESS")
                    require(json != null) {
                        Log.d("PANTRY ON SUCCESS", "ERROR: JSON EMPTY")
                    }
                    println("${json.jsonObject.getJSONArray("results")}")
                    //val recipeArray =  RecipeList(DataServices.fromJsonArray(json.jsonArray))
                    val recipeArray = DataServices.fromJsonArray(json.jsonObject.getJSONArray("results"))
                    println("${myAdapter.selectedList}")
                    val selectRecipeIntent = Intent(applicationContext, SelectRecipes::class.java)

                    // selectRecipeIntent.putExtra(EXTRA_RECIPE_LIST, recipeArray)
                    selectRecipeIntent.putParcelableArrayListExtra(EXTRA_RECIPE_LIST, recipeArray)
                    startActivity(selectRecipeIntent)

                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String?,
                    throwable: Throwable?
                ) {
                    Log.d("ON NEXT CLICKED", "onFAIL")
                    println("statusCode: $statusCode\nHeaders: $headers\nResponse: $response\n\nTHROWABLE: $throwable")

                }

            })

    }

    override fun getDiets(diet: String) {
        selectedDiet = diet //To change body of created functions use File | Settings | File Templates.
      //  Toast.makeText(this, selectedDiet, Toast.LENGTH_SHORT).show()
    }

    override fun getIntolerance(arr: ArrayList<CheckBox>) {
        arr.forEach{
            if(it.isChecked) {
               // println(it.text)
                intoleranceArr.add(it.text.toString())
            }
        }
        Log.i("GET_INTOLERANCE", intoleranceArr.toString())
    }
}


/*
            val client = AsyncHttpClient()
            val params = RequestParams()
            //https://api.spoonacular.com/recipes/findByIngredients?apiKey=dcfa3c8d24e24e41989b9848b981b874&ingredients=apples,flour,sugar&number=2
          *//*  params.put(PARAMS[PARAMS_BY_INGREDIENT_LIST], "apples,flour,sugar")
            params.put(PARAMS[PARAMS_BY_INGREDIENT_NUMBER], "2")*//*
            //params.put(PARAMS_BY_INGREDIENT_LIST, "apples,flour,sugar")
            params.put(PARAMS_BY_INGREDIENT_LIST, myAdapter.selectedList.toString())
            params.put("number", 2)

           *//* client.get("$API_URL$GET_RECIPES_BY_INGREDIENT_PATH$API_KEY", params, object: JsonHttpResponseHandler() {
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