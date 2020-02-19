package com.example.myrecipe.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.example.myrecipe.R
import com.example.myrecipe.services.DataServices
import com.example.myrecipe.utils.INGREDIENTS

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataServices.getIngredientListFull(this, INGREDIENTS)
        var actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setLogo(R.drawable.ic_logo_color)
        actionBar!!.setDisplayUseLogoEnabled(true)

    }

    fun onEnterClicked(view:View){
        val intentPantry = Intent(this,AddToPantry::class.java)
        startActivity(intentPantry)
    }

   /* fun getIngredientListFull(context: Context, fileName:String){

        println("THIS DIDNT WORK")
        val stream = this.resources.openRawResource(R.raw.ingredients)
           // val stream = assets.open(fileName)
            //val Stream = File("1kingredient.txt").inputStream()
            val reader = BufferedReader(stream.reader())
            //val content = StringBuilder()
            try {
                // val file = File(fileName)
                var line = reader.forEachLine {
                    val list = it.split(";")
                   // println("${List[0]} - ${List[1]}")
                    println(list)
                }
//        while (line != null) {
//            content.append(line)
//            line = reader.readLine()
//        }
            }catch (e : Exception){
                println("error-> $e:\n${e.stackTrace}")
            } finally {
                reader.close()
            }
           *//* val file = File( context.filesDir,fileName)
            //  require(file.canRead()) { "File must be readable. file=${fileName} canRead=${file.canRead()}" }
            file .forEachLine {
                val tempList =it.split(";")
                println("${tempList[0]} - ${tempList[1]}")
                DataServices.ingredientListFull.add(Ingredient(tempList[0], tempList[1]))
            }
        }catch (e : IOException){
            println("ERROR-> $e:\n${e.stackTrace}")
        }*//*

    }*/
}
