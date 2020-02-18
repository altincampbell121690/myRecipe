package com.example.myrecipe.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myrecipe.model.RecipeDetail.AnalyzedInstruction.Step
import com.example.myrecipe.utils.PAGER_MAX_COUNT
import com.example.myrecipe.view.InstructionsFragment
import java.util.ArrayList

class RecipePagerAdapter(fragActvty: FragmentActivity, private val steps: ArrayList<Step?>?): FragmentStateAdapter(fragActvty) {
    override fun getItemCount(): Int {
        return 3 * PAGER_MAX_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position % 3) {
            //1,3 -> FirstFragment.newInstance("FirstFragment, Instance 1")
            //else ->MovieFragment.newInstance(movies[position % movies.size]) // the remainder can only be between 0 -> n
            else -> InstructionsFragment.newInstance(steps)
        }
    }
}