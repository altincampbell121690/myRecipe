package com.example.myrecipe.adapter

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alespero.expandablecardview.ExpandableCardView
import com.bumptech.glide.Glide
import com.example.myrecipe.R
import com.example.myrecipe.model.ComplexRecipeInfo
import com.example.myrecipe.model.RecipeFromIngredient
import kotlinx.android.synthetic.main.recipe_card.view.*
import java.util.ArrayList

class RecipeCardAdapter(val context: Context, private val recipeList: ArrayList<ComplexRecipeInfo>, val recipeCardClick: (ComplexRecipeInfo)->Unit):RecyclerView.Adapter<RecipeCardAdapter.RecipeViewHolder>() {
    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val recipeImage = itemView.findViewById<ImageView>(R.id.ivRecipeCardImage)
        val recipeTitle = itemView.findViewById<TextView>(R.id.tvRecipeCardTitle)
        val recipeDetails = itemView.findViewById<ExpandableCardView>(R.id.ecDetails)
        val numMissing = itemView.findViewById<TextView>(R.id.tvnumMissing)
        val recipeInfo = itemView.findViewById<TextView>(R.id.tvMissingIngredients)

        //val recipeInfo = itemView.findViewById<TextView>(R.id.tvRecipeInfo)

        fun bindView(recipeItem:ComplexRecipeInfo){
            recipeInfo.movementMethod = ScrollingMovementMethod()
            recipeTitle.text = recipeItem.title
            Glide.with(context).load(recipeItem.image).into(recipeImage)
            val numstr = recipeItem.missedIngredientCount

            var str = "Missing Ingredients:\n"
            recipeItem.missedIngredients?.forEachIndexed{ i, it -> str += "- ${i+1} ) ${it?.original}\n"}
            recipeInfo.text = str
            numMissing.text = "You're missing ${numstr.toString()} items"
            println(numMissing.text)


            itemView.setOnClickListener{
                recipeCardClick(recipeItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
       val view =LayoutInflater.from(context).inflate(
           R.layout.recipe_card, parent,false
       )
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
       return recipeList.count()
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
       holder.bindView(recipeList[position])
    }
}