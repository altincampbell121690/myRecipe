package com.example.myrecipe.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class RecipeFromIngredient(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("missedIngredientCount") val missedIngredientCount: Int,
    @SerializedName("missedIngredients") val missedIngredients: List<Ingredient>,
    @SerializedName("title") val title: String,
    @SerializedName("unusedIngredients") val unusedIngredients: List<Ingredient>,// ingredient
    @SerializedName("usedIngredientCount") val usedIngredientCount: Int,
    @SerializedName("usedIngredients") val usedIngredients: List<Ingredient>) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Ingredient(
        @SerializedName("amount") val amount: Double,
        @SerializedName("id") val id: Int,
        @SerializedName("image") val image: String,
        @SerializedName("name") val name: String,
        @SerializedName("original") val original: String,
        @SerializedName("unit") val unit: String,
        @SerializedName("unitShort") val unitShort: String
    ) : Parcelable

}