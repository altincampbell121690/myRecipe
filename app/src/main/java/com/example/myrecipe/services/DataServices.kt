package com.example.myrecipe.services

import com.example.myrecipe.model.IngredientGET
import com.example.myrecipe.model.IngredientTest
import java.io.File
import java.io.IOException

object DataServices {
    val ingredientsList = mutableListOf(
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

    fun getIngredientListFull(fileName:String){
        try {
            val file = File(fileName)
            require(file.canRead()) { "File must be readable. file=${fileName} canRead=${file.canRead()}" }
            file.forEachLine {
                val tempList =it.split(";")
                println("${tempList[0]} - ${tempList[1]}")
                ingredientListFull.add(Ingredient(tempList[0], tempList[1]))
            }
        }catch (e : IOException){
            println("error-> $e:\n${e.stackTrace}")
        }

    }
}