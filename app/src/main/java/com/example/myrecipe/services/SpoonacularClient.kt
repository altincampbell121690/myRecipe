package com.example.myrecipe.services

import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.myrecipe.utils.*

object SpoonacularClient {
        var client = AsyncHttpClient()

        fun searchRecipeByIngredient(listOfIng:List<String>, rankingOption:String = "1", handler: JsonHttpResponseHandler){
            val params = RequestParams()
            params.put(PARAMS_BY_INGREDIENT_LIST, listOfIng.toString())
            params.put(PARAMS_BY_INGREDIENT_IGNORE_COMMON_ITEMS, "true")
            params.put(PARAMS_BY_INGREDIENT_NUMBER, "2")
            params.put(PARAMS_BY_INGREDIENT_RANKING, rankingOption) // might change
            client.get("$API_URL$GET_RECIPES_BY_INGREDIENT_PATH$API_KEY", params, handler)

        }

        fun getRecipeInformation(ID:String,handler: JsonHttpResponseHandler){
            val params = RequestParams()
           val id = "$ID/"
            params.put(PARAMS_RECIPE_INFORMATION_NUTRITION, "true")
            params.put(PARAMS_RECIPE_INFORMATION_INSTRUCTION, "true")
           // params.put("number", "20")
            client.get("$API_URL$id$GET_RECIPE_INFORMATION_PATH$API_KEY", params, handler)

        }

    fun complexSearch(
        listOfIng: List<String>,
        diet:String,
        listOfIntolerances:List<String>,
        handler: JsonHttpResponseHandler){
        val params = RequestParams()
        var dietX = diet
        if(dietX == "None")
            dietX = ""

        if (dietX != "") {
            params.put(PARAMS_COMPLEX_SEARCH_DIET,dietX)
        }
        if (listOfIntolerances.isNotEmpty()) {
            params.put(PARAMS_COMPLEX_SEARCH_INTOLERANCE_LIST, listOfIntolerances.toString())
        }
        params.put(PARAMS_COMPLEX_SEARCH_INSTRUCTIONS,"true")
       // params.put(PARAMS_COMPLEX_SEARCH_FILL_INGREDIENTS, "true") // gotta fix it here
        params.put(PARAMS_COMPLEX_SEARCH_RECIPE_INFO, "true")
        params.put(PARAMS_COMPLEX_SEARCH_INGREDIENTS_LIST,listOfIng.toString())
        params.put(PARAMS_COMPLEX_SEARCH_NUMBER,"10")
        // params.put("number", "20")
        client.get("$API_URL$COMPLEX_SEARCH$API_KEY", params, handler)

    }
}