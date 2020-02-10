package com.example.myrecipe.utils


const val API_KEY = "bd47c38ab5ba48449649784fd402f94e"
const val BACKEND_BASE_URL = ""
const val IMAGE_PATH = ""
const val API_URL = "https://api.spoonacular.com/recipes/"
const val GET_RECIPES_BY_INGREDIENT_PATH = "findByIngredients?apiKey="
const val GET_RECIPE_INFORMATION_PATH = "information?apiKey="
const val GET_RECIPE_INFORMATION_BULK_PATH = "informationBulk?apiKey="

// params for ingredients
const val PARAMS_BY_INGREDIENT_LIST = "ingredients"
const val PARAMS_BY_INGREDIENT_NUMBER = "number"
const val PARAMS_BY_INGREDIENT_RANKING = "ranking"
const val PARAMS_BY_INGREDIENT_IGNORE_COMMON_ITEMS = "ignore pantry"

//paramts for get recipe information
const val PARAMS_RECIPE_INFORMATION_NUTRITION = "include nutrition"
const val PARAMS_RECIPE_INFORMATION_INSTRUCTION = "include instructions"

// params map
val PARAMS = mapOf<String,String>("ingredients" to "&ingredients=", "number" to "&number=", "id" to "&ids=","include nutrition" to "&includeNutrition=true", "include instructions" to "&includeInstruction=true", "ranking" to "&ranking=","ignore pantry" to "&ignorePantry=true" )


// all ingredients
const val INGREDIENTS = "ingredients.txt"

// get parcelable
const val EXTRA_RECIPE_DATA: String = ""
