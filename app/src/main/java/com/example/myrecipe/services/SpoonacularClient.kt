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
}