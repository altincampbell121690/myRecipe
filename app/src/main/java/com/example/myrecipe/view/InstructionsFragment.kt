package com.example.myrecipe.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.myrecipe.R
import com.example.myrecipe.adapter.InstructionsListAdapter
import com.example.myrecipe.model.RecipeDetail.AnalyzedInstruction.Step
import com.example.myrecipe.utils.BUNDLE_RECIPE_STEPS

/**
 * A simple [Fragment] subclass.
 */
class InstructionsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Creates the view controlled by the fragment
        val args = arguments
        val context =  requireContext()
        val steps: ArrayList<Step>  = args?.getParcelableArrayList(BUNDLE_RECIPE_STEPS)!!
        val view = inflater.inflate(R.layout.fragment_instructions, container, false)
        val instructionList = view.findViewById<RecyclerView>(R.id.rvInstructionList)
        instructionList.adapter = InstructionsListAdapter(context ,steps)
        instructionList.layoutManager = LinearLayoutManager(context)
        // Inflate the layout for this fragment
        // and return the view
        return view
    }
    companion object {
        // Method for creating new instances of the fragment
        fun newInstance(steps: java.util.ArrayList<Step?>?): InstructionsFragment {
            //1  Store the movie data in a Bundle object

            val args = Bundle()
            args.putParcelableArrayList(BUNDLE_RECIPE_STEPS, steps)
            //2  Create a new MovieFragment and set the Bundle as the arguments
            //  to be retrieved and displayed when the view is created
            val frag = InstructionsFragment()
          // 3 set arguments
            frag.arguments = args
            return frag
        }
    }

}
