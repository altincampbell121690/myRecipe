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


class PantryRecyclerViewAdapter(val context: Context, private val pantry: ArrayList<ComplexRecipeInfo.Ingredient>): RecyclerView.Adapter<PantryRecyclerViewAdapter.PantryViewHolder>(){
    inner class PantryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var pantryItemImage = itemView.findViewById<ImageView>(R.id.ivPantryItemImage)
        var pantryItemName = itemView.findViewById<TextView>(R.id.tvPantryItemName)

        fun bindView(pantryItem:ComplexRecipeInfo.Ingredient){
            Glide.with(context).load(pantryItem.image).circleCrop().centerInside().into(pantryItemImage)
            pantryItemName.text = pantryItem.original
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