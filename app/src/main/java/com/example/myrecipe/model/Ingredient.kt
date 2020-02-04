package com.example.myrecipe.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Ingredient(
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nutrition")
    val nutrition: Nutrition
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Nutrition(
        @SerializedName("calories")
        val calories: Int,
        @SerializedName("Carbs")
        val carbs: Int,
        @SerializedName("Fat")
        val fat: Double,
        @SerializedName("Fiber")
        val fiber: Double,
        @SerializedName("protein")
        val protein: Double,
        @SerializedName("Sugar")
        val sugar: Double
    ) : Parcelable
}