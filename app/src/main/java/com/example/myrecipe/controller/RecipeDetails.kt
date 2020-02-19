package com.example.myrecipe.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.myrecipe.R
import com.example.myrecipe.adapter.RecipePagerAdapter
import com.example.myrecipe.model.ComplexRecipeInfo
import com.example.myrecipe.model.RecipeDetail
import com.example.myrecipe.utils.EXTRA_RECIPE_COMPLEX
import com.example.myrecipe.utils.EXTRA_RECIPE_DETAIL
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_recipe_details.*

class RecipeDetails : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: RecipePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        viewPager = findViewById(R.id.viewPager)
        val recipe: RecipeDetail? = intent.getParcelableExtra(EXTRA_RECIPE_DETAIL)
        val pantryItem: ComplexRecipeInfo? = intent.getParcelableExtra(EXTRA_RECIPE_COMPLEX)
        if (recipe != null){
            Glide.with(this).load(recipe.image).into(ivImageDetail)
            tvTitleDetail.text = recipe.title
            tvCookTimeDetail.text = "Ready in ${recipe.readyInMinutes.toString()} Minutes"
            tvServings.text = "Serves: ${recipe.servings}"

            val steps = recipe.analyzedInstructions!![0]!!.steps
            val nutrition = recipe.nutrition?.nutrients
            val onHand = pantryItem!!.usedIngredients
            val missing = pantryItem!!.missedIngredients

           pagerAdapter = RecipePagerAdapter(this,missing,onHand ,nutrition ,steps)
            viewPager.adapter = pagerAdapter
            viewPager.currentItem = pagerAdapter.itemCount / 2
            tabLayout = findViewById(R.id.tabs)
            TabLayoutMediator(tabLayout,viewPager,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    tab.text = when(position % 3){
                                    0 -> "Instructions"
                                    1 -> "Nutrition"
                                    else -> "More Information"
                            }
                }
            ).attach()

        }else{
            Toast.makeText(this,"Sorry something went wrong", Toast.LENGTH_LONG).show()
        }


    }
}
