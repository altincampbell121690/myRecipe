package com.example.myrecipe.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.myrecipe.R
import com.example.myrecipe.adapter.InstructionsListAdapter
import com.example.myrecipe.model.RecipeDetail
import com.example.myrecipe.model.RecipeDetail.Nutrition.Nutrient
import com.example.myrecipe.utils.BUNDLE_RECIPE_NUTRITION
import com.example.myrecipe.utils.BUNDLE_RECIPE_STEPS

/**
 * A simple [Fragment] subclass.
 */
class NutritionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val args = arguments
        val context =  requireContext()
        val nutrition: ArrayList<Nutrient>  = args?.getParcelableArrayList(BUNDLE_RECIPE_NUTRITION)!!
        val view = inflater.inflate(R.layout.fragment_nutrition, container, false)
        val  cals= view.findViewById<TextView>(R.id.tvCalories)
        val  fat= view.findViewById<TextView>(R.id.tvFat)
        val  satFat= view.findViewById<TextView>(R.id.tvSatFat)
        val  carbs= view.findViewById<TextView>(R.id.tvCarbs)
        val  fiber= view.findViewById<TextView>(R.id.tvFiber)
        val  sugar= view.findViewById<TextView>(R.id.tvSugar)
        val  protien= view.findViewById<TextView>(R.id.tvProtien)
      cals.text = nutrition
        // Inflate the layout for this fragment
        // and return the view
        return view
    }

    companion object {
        // Method for creating new instances of the fragment
        fun newInstance(usedIngredients:ArrayList<Nutrient?>?): InstructionsFragment {
            //1  Store the movie data in a Bundle object

            val args = Bundle()
            args.putParcelableArrayList(BUNDLE_RECIPE_NUTRITION, usedIngredients)
            //2  Create a new MovieFragment and set the Bundle as the arguments
            //  to be retrieved and displayed when the view is created
            val frag = InstructionsFragment()
            // 3 set arguments
            frag.arguments = args
            return frag
        }
    }


}
