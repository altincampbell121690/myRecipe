package com.example.myrecipe.services

import com.example.myrecipe.model.IngredientTest

object DataServices {
    val ingredientsList = mutableListOf(
        IngredientTest("Broccoli","broccoli"),
        IngredientTest("Chicken","chicken"),
        IngredientTest("Swiss Cheese", "swiss_cheese"),
        IngredientTest("Lamb Shank","lamb"),
        IngredientTest("Carrot","carrot"),
        IngredientTest("Cheddar Cheese", "cheddar_cheese")
    )

    // not safe!
    val selectedIngredientsList = mutableListOf<IngredientTest>()
}