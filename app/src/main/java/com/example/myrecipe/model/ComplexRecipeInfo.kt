package com.example.myrecipe.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class ComplexRecipeInfo(
    @SerializedName("analyzedInstructions")
    var analyzedInstructions: List<AnalyzedInstruction?>? = null,
    @SerializedName("cookingMinutes")
    var cookingMinutes: Int? = null,
    @SerializedName("creditsText")
    var creditsText: String? = null,
    @SerializedName("cuisines")
    var cuisines: List<String?>? = null,
    @SerializedName("diets")
    var diets: List<String?>? = null,
    @SerializedName("dishTypes")
    var dishTypes: List<String?>? = null,
    @SerializedName("healthScore")
    var healthScore: Double? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("likes")
    var likes: Int? = null,
    @SerializedName("missedIngredientCount")
    var missedIngredientCount: Int? = null,
    @SerializedName("missedIngredients")
    var missedIngredients: ArrayList<Ingredient?>? = null,
    @SerializedName("preparationMinutes")
    var preparationMinutes: Int? = null,
    @SerializedName("pricePerServing")
    var pricePerServing: Double? = null,
    @SerializedName("readyInMinutes")
    var readyInMinutes: Int? = null,
    @SerializedName("servings")
    var servings: Int? = null,
    @SerializedName("sourceName")
    var sourceName: String? = null,
    @SerializedName("sourceUrl")
    var sourceUrl: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("unusedIngredients")
    var unusedIngredients: List<Ingredient?>? = null,
    @SerializedName("usedIngredientCount")
    var usedIngredientCount: Int? = null,
    @SerializedName("usedIngredients")
    var usedIngredients: ArrayList<Ingredient?>? = null,
    @SerializedName("winePairing")
    var winePairing: WinePairing? = null
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class AnalyzedInstruction(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("steps")
        var steps: List<Step?>? = null
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Step(
            @SerializedName("ingredients")
            var ingredients: List<Ingredient?>? = null,
            @SerializedName("length")
            var length: Length? = null,
            @SerializedName("number")
            var number: Int? = null,
            @SerializedName("step")
            var step: String? = null
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Length(
                @SerializedName("number")
                var number: Int? = null,
                @SerializedName("unit")
                var unit: String? = null
            ) : Parcelable
        }
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Ingredient(
        @SerializedName("aisle")
        var aisle: String? = null,
        @SerializedName("amount")
        var amount: Double? = null,
        @SerializedName("extendedName")
        var extendedName: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("meta")
        var meta: List<String?>? = null,
        @SerializedName("metaInformation")
        var metaInformation: List<String?>? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("original")
        var original: String? = null,
        @SerializedName("originalName")
        var originalName: String? = null,
        @SerializedName("originalString")
        var originalString: String? = null,
        @SerializedName("unit")
        var unit: String? = null,
        @SerializedName("unitLong")
        var unitLong: String? = null,
        @SerializedName("unitShort")
        var unitShort: String? = null
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class WinePairing(
        @SerializedName("pairedWines")
        var pairedWines: List<String?>? = null,
        @SerializedName("pairingText")
        var pairingText: String? = null
    ) : Parcelable
}