package com.example.myrecipe.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.myrecipe.R
import kotlinx.android.synthetic.main.modal_layout.*

class FilterDialog : DialogFragment(), AdapterView.OnItemSelectedListener {
    var diet = ""
    lateinit var arrIntolerances: ArrayList<CheckBox>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflator = activity!!.layoutInflater
        val view = inflator.inflate(R.layout.modal_layout, null)
       arrIntolerances = arrayListOf(view.findViewById(R.id.cbDairy),
           view.findViewById(R.id.cbEgg),
           view.findViewById(R.id.cbGluten),
           view.findViewById(R.id.cbGrain),
           view.findViewById(R.id.cbPeanut),
           view.findViewById(R.id.cbSeafood),
           view.findViewById(R.id.cbSesame),
           view.findViewById(R.id.cbSoy),
           view.findViewById(R.id.cbSulfite),
           view.findViewById(R.id.cbTreeNut),
           view.findViewById(R.id.cbWheat))
        diet = ""
        val adapter = ArrayAdapter.createFromResource(
            activity!!,
            R.array.Diets,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        val Diets = view.findViewById<Spinner>(R.id.spnrDiet)
        Diets.adapter = adapter
        Diets.onItemSelectedListener = this
        builder.setView(view).setTitle("Filter By")
            .setNegativeButton("cancel", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    diet = ""
                } // do nothing
            })
            .setPositiveButton("Finish") { dialog, which ->
                Toast.makeText(context,"FINISHED $diet", Toast.LENGTH_LONG).show()
            }
        //diet =
        return builder.create()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(parent?.getItemAtPosition(position).toString() != "None"){
            Toast.makeText(parent!!.context,"I am selected", Toast.LENGTH_SHORT).show()
            diet = parent.getItemAtPosition(position).toString()

        }



    }
}