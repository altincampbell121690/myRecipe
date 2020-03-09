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
import com.example.myrecipe.model.ComplexRecipeInfo
import com.example.myrecipe.utils.IMAGE_URL


class PantryRecyclerViewAdapter(val context: Context, private val pantry: ArrayList<ComplexRecipeInfo.Ingredient>): RecyclerView.Adapter<PantryRecyclerViewAdapter.PantryViewHolder>(){
    inner class PantryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var pantryItemImage = itemView.findViewById<ImageView>(R.id.ivPantryItemImage)
        var pantryItemName = itemView.findViewById<TextView>(R.id.tvPantryItemName)
//"$IMAGE_URL${recipeItem.image}"
        fun bindView(pantryItem:ComplexRecipeInfo.Ingredient){
        if(pantryItem.image!!.contains(IMAGE_URL)){
            Glide.with(context).load(pantryItem.image).circleCrop().centerInside().into(pantryItemImage)
        }else{
            Glide.with(context).load("$IMAGE_URL${pantryItem.image}").circleCrop().centerInside().into(pantryItemImage)
        }
              pantryItemName.text = pantryItem.name
    println("$IMAGE_URL${pantryItem.image}")
    println("text: ${pantryItem.original}")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PantryViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.pantry_item,parent, false)
        return PantryViewHolder(view)
    }

    override fun getItemCount(): Int {
       return pantry.count()
    }

    override fun onBindViewHolder(holder: PantryViewHolder, position: Int) {
        holder.bindView(pantry[position])
    }
}