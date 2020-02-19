package com.example.myrecipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipe.R
import com.example.myrecipe.model.RecipeDetail
import com.example.myrecipe.model.RecipeDetail.AnalyzedInstruction.Step
import kotlinx.android.synthetic.main.instruction_list_item.view.*

class InstructionsListAdapter(val context: Context,val instructionList:ArrayList<Step>): RecyclerView.Adapter<InstructionsListAdapter.InstructionViewHolder>() {
    inner class InstructionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var instruction = itemView.findViewById<CheckBox>(R.id.cbInstruction)

        fun bindView(step: Step){
            instruction.text = "${step.number}) ${step.step}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.instruction_list_item,parent, false)
        return InstructionViewHolder(view)
    }

    override fun getItemCount(): Int {
       return instructionList.count()
    }

    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
        holder.bindView(instructionList[position])
    }
}