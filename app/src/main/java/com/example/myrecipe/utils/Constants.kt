package com.example.myrecipe.utils


const val API_KEY = "fce02cd9a52d483fb7de3a6b0ec1a649"
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
const val PARAMS_RECIPE_INFORMATION_NUTRITION = "includeNutrition"
const val PARAMS_RECIPE_INFORMATION_INSTRUCTION = "includeInstruction"

// params map
//val PARAMS = mapOf<String,String>("ingredients" to "&ingredients=", "number" to "&number=", "id" to "&ids=","include nutrition" to "&includeNutrition=true", "include instructions" to "&includeInstruction=true", "ranking" to "&ranking=","ignore pantry" to "&ignorePantry=true" )


// all ingredients
const val INGREDIENTS = "ingredients.txt"

// get parcelable

const val EXTRA_RECIPE_LIST: String = "recipe list"
const val EXTRA_RECIPE_DETAIL: String = "recipe details"

const val BUNDLE_RECIPE_STEPS= "step"
const val BUNDLE_RECIPE_NUTRITION= "nutrients"

const val PAGER_MAX_COUNT = 200