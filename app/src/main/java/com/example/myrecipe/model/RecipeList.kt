package com.example.myrecipe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecipeList(val recipeList: ArrayList<RecipeFromIngredient>) : Parcelable