package com.example.myrecipe.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import java.util.ArrayList

@SuppressLint("ParcelCreator")
@Parcelize
data class RecipeDetail(
    @SerializedName("analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstruction?>? = null,
    @SerializedName("cuisines")
    val cuisines: List<String?>? = null,
    @SerializedName("dairyFree")
    val dairyFree: Boolean? = null,
    @SerializedName("diets")
    val diets: List<String?>? = null,
    @SerializedName("dishTypes")
    val dishTypes: List<String?>? = null,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient?>? = null,
    @SerializedName("gaps")
    val gaps: String? = null,
    @SerializedName("glutenFree")
    val glutenFree: Boolean? = null,
    @SerializedName("healthScore")
    val healthScore: Double? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("instructions")
    val instructions: String? = null,
    @SerializedName("lowFodmap")
    val lowFodmap: Boolean? = null,
    @SerializedName("nutrition")
    val nutrition: Nutrition? = null,
    @SerializedName("occasions")
    val occasions: List<String?>? = null,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int? = null,
    @SerializedName("servings")
    val servings: Int? = null,
    @SerializedName("sourceUrl")
    val sourceUrl: String? = null,
    @SerializedName("spoonacularSourceUrl")
    val spoonacularSourceUrl: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vegan")
    val vegan: Boolean? = null,
    @SerializedName("vegetarian")
    val vegetarian: Boolean? = null,
    @SerializedName("veryHealthy")
    val veryHealthy: Boolean? = null,
    @SerializedName("veryPopular")
    val veryPopular: Boolean? = null,
    @SerializedName("weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int? = null,
    @SerializedName("winePairing")
    val winePairing: WinePairing? = null
) : Parcelable {

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class AnalyzedInstruction(
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("steps")
        val steps: ArrayList<Step?>? = null // LIST
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Step(
            @SerializedName("equipment")
            val equipment: List<Equipment?>? = null,
            @SerializedName("ingredients")
            val ingredients: List<RecipeIngredient?>? = null,
            @SerializedName("number")
            val number: Int? = null,
            @SerializedName("step")
            val step: String? = null
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Equipment(
                @SerializedName("id")
                val id: Int? = null,
                @SerializedName("image")
                val image: String? = null,
                @SerializedName("name")
                val name: String? = null
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class RecipeIngredient(
                @SerializedName("id")
                val id: Int,
                @SerializedName("image")
                val image: String,
                @SerializedName("name")
                val name: String
            ) : Parcelable
        }
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class ExtendedIngredient(
        @SerializedName("amount")
        val amount: Double? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("image")
        val image: String? = null,
        @SerializedName("measures")
        val measures: Measures? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("original")
        val original: String? = null,
        @SerializedName("originalName")
        val originalName: String? = null,
        @SerializedName("unit")
        val unit: String? = null
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Measures(
            @SerializedName("us")
            val us: Us? = null
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Us(
                @SerializedName("amount")
                val amount: Double? = null,
                @SerializedName("unitLong")
                val unitLong: String? = null,
                @SerializedName("unitShort")
                val unitShort: String? = null
            ) : Parcelable
        }
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Nutrition(
        @SerializedName("caloricBreakdown")
        val caloricBreakdown: CaloricBreakdown? = null,
        @SerializedName("ingredients")
        val ingredients: List<Ingredient?>? = null,
        @SerializedName("nutrients")
        val nutrients: ArrayList<Nutrient?>? = null,
        @SerializedName("weightPerServing")
        val weightPerServing: WeightPerServing? = null
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class CaloricBreakdown(
            @SerializedName("percentCarbs")
            val percentCarbs: Double? = null,
            @SerializedName("percentFat")
            val percentFat: Double? = null,
            @SerializedName("percentProtein")
            val percentProtein: Double? = null
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Ingredient(
            @SerializedName("amount")
            val amount: Double? = null,
            @SerializedName("name")
            val name: String? = null
        ) : Parcelable
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Nutrient(
            @SerializedName("amount")
            val amount: Double? = null,
            @SerializedName("percentOfDailyNeeds")
            val percentOfDailyNeeds: Double? = null,
            @SerializedName("title")
            val title: String? = null,
            @SerializedName("unit")
            val unit: String? = null
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class WeightPerServing(
            @SerializedName("amount")
            val amount: Int? = null,
            @SerializedName("unit")
            val unit: String? = null
        ) : Parcelable
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class WinePairing(
        @SerializedName("pairedWines")
        val pairedWines: List<String?>? = null,
        @SerializedName("pairingText")
        val pairingText: String? = null
    ) : Parcelable
}