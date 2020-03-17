package com.example.myrecipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myrecipe.controller.AddToPantry
import kotlinx.android.synthetic.main.activity_persona.*

class persona : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persona)
    }

    fun onfabClicked(view: View) {
        if(!etName.text.isNullOrBlank() and !etAge.text.isNullOrEmpty() and !etHeight.text.isNullOrEmpty() and !etWeight.text.isNullOrEmpty() and !etDWeight.text.isNullOrEmpty()){
            val intentPantry = Intent(this, AddToPantry::class.java)
            startActivity(intentPantry)
        }
        else{
            Toast.makeText(this,"please fill out each field",Toast.LENGTH_LONG).show()
        }
    }
}
