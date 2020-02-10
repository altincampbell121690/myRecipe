package com.example.myrecipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrecipe.R
import com.example.myrecipe.model.RecipeFromIngredient
import kotlinx.android.synthetic.main.recipe_card.view.*

class RecipeCardAdapter(val context: Context, val recipeList: MutableList<RecipeFromIngredient>, val recipeCardClick: (RecipeFromIngredient)->Unit):RecyclerView.Adapter<RecipeCardAdapter.RecipeViewHolder>() {
    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val recipeImage = itemView.findViewById<ImageView>(R.id.ivRecipeCardImage)
        val recipeTitle = itemView.findViewById<TextView>(R.id.tvRecipeCardTitle)
        val recipeInfo = itemView.findViewById<TextView>(R.id.tvRecipeInfo)

        fun bindView(recipeItem:RecipeFromIngredient){
            recipeTitle.text = recipeItem.title
            Glide.with(context).load(recipeItem.image).into(recipeImage)
            var str = "Missing Ingredients: "
            recipeItem.missedIngredients.forEach{str += " $it"}
            recipeInfo.text = str



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