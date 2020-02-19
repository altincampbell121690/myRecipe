package com.example.myrecipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipe.R
import com.example.myrecipe.model.RecipeDetail
import com.example.myrecipe.model.RecipeDetail.Nutrition.Nutrient
import kotlinx.android.synthetic.main.nutrition_item.view.*

class nutritionAdapter(val context: Context, val nutritionArr :ArrayList<Nutrient>): RecyclerView.Adapter<nutritionAdapter.nutritionViewHolder>(){
    inner class nutritionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.name)
        val amount = itemView.findViewById<TextView>(R.id.tvAmount)
        val percent = itemView.findViewById<TextView>(R.id.tvPercentDaily)

        fun bindView(nutrition:Nutrient ){
            title.text = nutrition.title
            amount.text = "${nutrition.amount} ${nutrition.unit}"
            percent.text = "${nutrition.percentOfDailyNeeds} %"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): nutritionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nutrition_item, parent,false)
        return nutritionViewHolder(view)
    }

    override fun getItemCount(): Int {
      return nutritionArr.count()
    }

    override fun onBindViewHolder(holder: nutritionViewHolder, position: Int) {
       holder.bindView(nutritionArr[position])
    }
}