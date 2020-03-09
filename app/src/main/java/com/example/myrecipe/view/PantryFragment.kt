package com.example.myrecipe.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.myrecipe.R
import com.example.myrecipe.adapter.PantryRecyclerViewAdapter
import com.example.myrecipe.model.ComplexRecipeInfo
import com.example.myrecipe.model.ComplexRecipeInfo.Ingredient
import com.example.myrecipe.utils.BUNDLE_RECIPE_PANTRY_MISSING
import com.example.myrecipe.utils.BUNDLE_RECIPE_PANTRY_ON_HAND

/**
 * A simple [Fragment] subclass.
 */
class PantryFragment : Fragment() {
    var onHand: ArrayList<Ingredient> = arrayListOf()
    var missing: ArrayList<Ingredient> = arrayListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Creates the view controlled by the fragment
        val args = arguments
        val context =  requireContext()
        onHand = try {
            args?.getParcelableArrayList(BUNDLE_RECIPE_PANTRY_ON_HAND)!!
        } catch (e: Exception) {
            println("PANTRY: FAILED CUZ ITS EMPTY")
            println("${e.toString()}:\n ${e.stackTrace}")
            arrayListOf()
        }
        missing = try {
            args?.getParcelableArrayList(BUNDLE_RECIPE_PANTRY_MISSING)!!
        } catch (e: Exception) {
            println("PARSE FAIL-PANTRY:FAILED CUZ ITS EMPTY")
            println("${e.toString()}:\n ${e.stackTrace}")
            arrayListOf()
        }
        val view = inflater.inflate(R.layout.fragment_pantry, container, false)
        val missedIngredientList = view.findViewById<RecyclerView>(R.id.rvMissedIngredients)
        missedIngredientList.adapter = PantryRecyclerViewAdapter(context ,missing)
        missedIngredientList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val onHandIngredientsList = view.findViewById<RecyclerView>(R.id.rvCurrentIngredients)
        onHandIngredientsList.adapter = PantryRecyclerViewAdapter(context ,onHand)
        onHandIngredientsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        // Inflate the layout for this fragment
        // and return the view
        return view
    }
    companion object {
        // Method for creating new instances of the fragment
        fun newInstance(MissedIngredients: ArrayList<Ingredient?>?, onHandIngredients: ArrayList<Ingredient?>?): PantryFragment {
            //1  Store the movie data in a Bundle object

            val args = Bundle()
            args.putParcelableArrayList(BUNDLE_RECIPE_PANTRY_MISSING, MissedIngredients)
            args.putParcelableArrayList(BUNDLE_RECIPE_PANTRY_ON_HAND, onHandIngredients)
            //2  Create a new MovieFragment and set the Bundle as the arguments
            //  to be retrieved and displayed when the view is created
            val frag = PantryFragment()
            // 3 set arguments
            frag.arguments = args
            return frag
        }
    }

}
