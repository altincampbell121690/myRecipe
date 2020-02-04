package com.example.myrecipe.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.example.myrecipe.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun onEnterClicked(view:View){
        val intentPantry = Intent(this,AddToPantry::class.java)
        startActivity(intentPantry)
    }
}
