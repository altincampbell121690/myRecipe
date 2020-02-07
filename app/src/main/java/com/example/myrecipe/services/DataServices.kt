package com.example.myrecipe.services

import android.content.Context
import com.example.myrecipe.R
import com.example.myrecipe.model.IngredientGET
import com.example.myrecipe.model.IngredientTest
import java.io.BufferedReader
import java.io.File
import java.io.IOException

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

    val ingredientListFull = mutableListOf<Ingredient>()

    fun getIngredientListFull(context: Context,fileName:String){
        val stream = context.resources.openRawResource(R.raw.ingredients)
        val reader = BufferedReader(stream.reader())
        println("THIS DIDNT WORK")
        try {
         reader.forEachLine {
             val tempList = it.split(";")
             ingredientListFull.add(Ingredient(tempList[0], tempList[1]))
             println("${tempList[0]} - ${tempList[1]}")
         }
        }catch (e : Exception){
            println("ERROR-> $e:\n${e.stackTrace}")
        }finally {
            reader.close()
        }

    }
}
