package com.example.myrecipe.model

class IngredientTest(val name:String, val image:String, var isChecked:Boolean = false) {
    override fun toString(): String {
        return name
    }
}