package com.example.myrecipe.services

import android.content.Context
import android.util.Log
import com.example.myrecipe.R
import com.example.myrecipe.model.IngredientItem
import com.example.myrecipe.model.IngredientTest
import com.example.myrecipe.model.RecipeFromIngredient
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

object DataServices {
    val ingredientsList = mutableListOf( // for testing
        IngredientTest("Broccoli","broccoli"),
        IngredientTest("Chicken","chicken"),
        IngredientTest("Swiss Cheese", "swiss_cheese"),
        IngredientTest("Lamb Shank","lamb"),
        IngredientTest("Carrot","carrot"),
        IngredientTest("Cheddar Cheese", "cheddar_cheese") ,
        IngredientTest("Broccoli","broccoli"),
    IngredientTest("Chicken","chicken"),
    IngredientTest("Swiss Cheese", "swiss_cheese"),
    IngredientTest("Lamb Shank","lamb"),
    IngredientTest("Carrot","carrot"),
    IngredientTest("Cheddar Cheese", "cheddar_cheese")

    )

    // not safe!
    val selectedIngredientsList = mutableListOf<IngredientTest>()

    val ingredientListFull = mutableListOf<IngredientItem>()

    fun getIngredientListFull(context: Context,fileName:String){
        val stream = context.resources.openRawResource(R.raw.ingredients)
        val reader = BufferedReader(stream.reader())
        println("THIS DIDNT WORK")
        try {
         reader.forEachLine {
             val tempList = it.split(";")
             ingredientListFull.add(
                 IngredientItem(
                     tempList[0],
                     tempList[1]
                 )
             )
             println("${tempList[0]} - ${tempList[1]}")
         }
        }catch (e : Exception){
            println("ERROR-> $e:\n${e.stackTrace}")
        }finally {
            reader.close()
        }

    }
    @Throws(JSONException::class)
    fun fromJsonArray(jsonArray: JSONArray): ArrayList<RecipeFromIngredient>{
        var recipeList = ArrayList<RecipeFromIngredient>()
        var gson = Gson()
        for(i in 0 until jsonArray.length()){
            recipeList.add(gson.fromJson(jsonArray[i].toString(), RecipeFromIngredient::class.java))
        }
        Log.i("DATA SERV JSON ARR", recipeList[0].title)
        return recipeList
    }
    fun toIngredientList(ingredientList:MutableList<IngredientItem>):List<String>{
        val listofStr = mutableListOf<String>()

            ingredientList.forEach {
                listofStr.add(it.name)
            }
        return listofStr

    }

}
