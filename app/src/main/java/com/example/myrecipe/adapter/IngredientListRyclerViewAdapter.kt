package com.example.myrecipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipe.R
import com.example.myrecipe.model.IngredientTest
import com.example.myrecipe.services.DataServices

//class IngredientListRyclerViewAdapter(private val context: Context, private var ingredientList: MutableList<IngredientTest>) : RecyclerView.Adapter<IngredientListRyclerViewAdapter.IngredientViewHolder>(){
//,val itemChecked: (CompoundButton, Boolean)-> Unit
class IngredientListRyclerViewAdapter(private val context: Context, private var ingredientList: MutableList<IngredientTest>)  : RecyclerView.Adapter<IngredientListRyclerViewAdapter.IngredientViewHolder>(), Filterable{
    var copyIngredientList= ingredientList.filter{it == it}
    var selectedList = mutableListOf<IngredientTest>()
    inner class IngredientViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ingredientImage = itemView.findViewById<ImageView>(R.id.ivIngredient)
        val ingredientName = itemView.findViewById<CheckBox>(R.id.cbItemName)

        fun bindIngredient(ingredient: IngredientTest?, context: Context){
            val resourceId = context.resources.getIdentifier(ingredient?.image,"drawable", context.packageName)
            ingredientImage.setImageResource(resourceId)
            ingredientName.text = ingredient?.name
            ingredientName.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    Toast.makeText(context, "im checked", Toast.LENGTH_SHORT).show()
                    //ingredient?.let { DataServices.selectedIngredientsList.add(it) }

                    ingredient?.let { selectedList.add(it) }
                }

                else {
                    Toast.makeText(context, "NOT checked", Toast.LENGTH_SHORT).show()
                    selectedList.remove(ingredient)
                }
            }
            //ingredientName.setOnCheckedChangeListener(itemChecked)
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.ingredient_list_item, parent, false
        )
        return IngredientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (ingredientList.isNullOrEmpty()) 0 else ingredientList.count()
    }

    override fun onBindViewHolder(myViewholder: IngredientViewHolder, position: Int) {
        myViewholder.bindIngredient(ingredientList[position], context)

    }


    override fun getFilter(): Filter {
        return IngredientFilter()
    }
    inner class IngredientFilter : Filter() {
        // must create the filter

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            // atomatically run on the backgroudn thread
            val filteredList = if (constraint.isNullOrBlank()){
                copyIngredientList.filter { it == it } // try to add all
            }else{
                val filterBy = constraint.toString().toLowerCase().trim()
                copyIngredientList.filter {it.name.toLowerCase().contains(filterBy)}
            }
            val filterResults = FilterResults()
            require(filteredList is List<IngredientTest>) {
                println("ERROR invalid filter list type")

            }
            filterResults.values = filteredList
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            ingredientList = mutableListOf()
            val list : List<Any?> = results?.values as List<Any?>
            ingredientList.addAll(list as List<IngredientTest>)
            notifyDataSetChanged()

        }
    }

}

