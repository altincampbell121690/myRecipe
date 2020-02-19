package com.example.myrecipe.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myrecipe.model.ComplexRecipeInfo
import com.example.myrecipe.model.RecipeDetail
import com.example.myrecipe.model.RecipeDetail.AnalyzedInstruction.Step
import com.example.myrecipe.model.RecipeDetail.Nutrition.Nutrient
import com.example.myrecipe.utils.PAGER_MAX_COUNT
import com.example.myrecipe.view.InstructionsFragment
import com.example.myrecipe.view.NutritionFragment
import com.example.myrecipe.view.PantryFragment
import java.util.ArrayList

class RecipePagerAdapter(fragActvty: FragmentActivity, private val missedIngredients: ArrayList<ComplexRecipeInfo.Ingredient?>?,private val onHandIngredients: ArrayList<ComplexRecipeInfo.Ingredient?>?, private val nutrion:ArrayList<Nutrient?>?, private val steps: ArrayList<Step?>?): FragmentStateAdapter(fragActvty) {
    override fun getItemCount(): Int {
        return 3 * PAGER_MAX_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        if (steps.isNullOrEmpty()){
            println("ITS NULLLLLLLLLLLLLLLLLLLL")
        }
        return when (position % 3) {
            0 -> InstructionsFragment.newInstance(steps)
            //else ->MovieFragment.newInstance(movies[position % movies.size]) // the remainder can only be between 0 -> n
            1-> PantryFragment.newInstance(missedIngredients, onHandIngredients)
            else -> NutritionFragment.newInstance(nutrion)
        }
    }
}