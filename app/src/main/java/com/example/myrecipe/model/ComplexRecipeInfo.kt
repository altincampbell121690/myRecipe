package com.example.myrecipe.model


import android.annotation.SuppressLint
import android.os.Parcelable
import android.util.Log
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ComplexRecipeInfo(
    @SerializedName("analyzedInstructions")
    var analyzedInstructions: List<AnalyzedInstruction?>? = null,
    @SerializedName("cookingMinutes")
    var cookingMinutes: Int? = null,
    @SerializedName("cuisines")
    var cuisines: List<String?>? = null,
    @SerializedName("diets")
    var diets: List<String?>? = null,
    @SerializedName("healthScore")
    var healthScore: Double? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("likes")
    var likes: Int? = null,
    @SerializedName("missedIngredientCount")
    var missedIngredientCount: Int? = null,
    @SerializedName("missedIngredients")
    var missedIngredients: ArrayList<Ingredient?>? = null,
    @SerializedName("usedIngredients")
    var usedIngredients: ArrayList<Ingredient?>? = null,
    @SerializedName("preparationMinutes")
    var preparationMinutes: Int? = null,
    @SerializedName("readyInMinutes")
    var readyInMinutes: Int? = null,
    @SerializedName("servings")
    var servings: Int? = null,
    @SerializedName("sourceName")
    var sourceUrl: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("usedIngredientCount")
    var usedIngredientCount: Int? = null,
    @SerializedName("winePairing")
    var winePairing: WinePairing? = null) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class AnalyzedInstruction(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("steps")
        var steps: List<Step?>? = null
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Step(
            @SerializedName("ingredients")
            var ingredients: List<Ingredient?>? = null,
            @SerializedName("length")
            var length: Length? = null,
            @SerializedName("number")
            var number: Int? = null,
            @SerializedName("step")
            var step: String? = null
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Length(
                @SerializedName("number")
                var number: Int? = null,
                @SerializedName("unit")
                var unit: String? = null
            ) : Parcelable
        }
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Ingredient(
        @SerializedName("amount")
        var amount: Double? = null,
        @SerializedName("extendedName")
        var extendedName: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("meta")
        var meta: List<String?>? = null,
        @SerializedName("metaInformation")
        var metaInformation: List<String?>? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("original")
        var original: String? = null,
        @SerializedName("originalName")
        var originalName: String? = null,
        @SerializedName("originalString")
        var originalString: String? = null,
        @SerializedName("unit")
        var unit: String? = null,
        @SerializedName("unitLong")
        var unitLong: String? = null,
        @SerializedName("unitShort")
        var unitShort: String? = null
    ) : Parcelable {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Ingredient

            if (name != other.name) return false

            return true
        }

        override fun hashCode(): Int {
            return name?.hashCode() ?: 0
        }
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class WinePairing(
        @SerializedName("pairedWines")
        var pairedWines: List<String?>? = null,
        @SerializedName("pairingText")
        var pairingText: String? = null
    ) : Parcelable



    fun setMissedIngredients(onHand: List<String>, extendedIngredients: List<Ingredient?>?) {

        missedIngredients = try {
            extendedIngredients!!.filterNot {
                onHand!!.contains(it!!.name) or  usedIngredients!!.contains(it)
            } as ArrayList<Ingredient?>?
        } catch (e: Exception) {
            println("ERROR: ${e.stackTrace}")
            null
        }

    }

    fun setUsedIngredients(onHand: List<String>, extendedIngredients: List<Ingredient?>?) {
        var i = 0; var j = 0;
        var flag = false
        var ingrList = mutableListOf<Ingredient?>()
        val onHandTemp = onHand.sorted()
        val ingListTemp = extendedIngredients!!.sortedBy { it!!.name }

// we need to run through the itteration and make sure this works

        while(i < onHand.size && j < extendedIngredients!!.size){
            Log.i("COMPLEX INFO", "${onHandTemp[i].first()} = : = ${ingListTemp[j]!!.name!!.first()}")
            if(!flag and !onHandTemp[i].first().equals(ingListTemp[j]!!.name!!.first(), ignoreCase = true) ){
                j++
                continue
            }
            else if(!flag and onHandTemp[i].first().equals(ingListTemp[j]!!.name!!.first(),ignoreCase = true)){
                flag = true
            }else if(flag and !onHandTemp[i].first().equals(ingListTemp[j]!!.name!!.first(),ignoreCase = true)){
                flag = false
                i++
                continue
            }
            if(flag && onHandTemp[i].first().equals(ingListTemp[j]!!.name!!.first(),ignoreCase = true)){
                println("${ingListTemp[j]!!.name!!} = = = ${onHandTemp[i]}")
                if (ingListTemp[j]!!.name!!.contains(onHandTemp[i]) or onHandTemp[i].contains(ingListTemp[j]!!.name!!)){
                    ingrList.add(ingListTemp[j])
                    Log.i("COMPLEX INFO INSIDE", "$ingrList")
                }
                Log.i("COMPLEX INFO", "$ingrList")
                j++
            }
        }
        usedIngredients = ingrList as ArrayList<Ingredient?>
/*        usedIngredients = try {
           extendedIngredients!!.filter{
                onHand!!.contains(
                    it!!.name
                )
            } as ArrayList<Ingredient?>?
        } catch (e: Exception) {
            println("ERROR: ${e.stackTrace}")
            null
        }*/
    }


   /*
    fun setMissedIngredients(onHand: List<String>) {

        missedIngredients = try {
            ingredienListtSetter(this.analyzedInstructions?.get(0)?.steps).filterNot {
                onHand.contains(it.name)
            } as ArrayList<Ingredient?>?
        } catch (e: Exception) {
            println("ERROR: ${e.stackTrace}")
            null
        }
    }

    fun setUsedIngredients(onHand: List<String>) {
        usedIngredients = try {
            ingredienListtSetter(this.analyzedInstructions?.get(0)?.steps).filter{
                onHand.contains(
                    it.name
                )
            } as ArrayList<Ingredient?>?
        } catch (e: Exception) {
            println("ERROR: ${e.stackTrace}")
            null
        }
    }

    private fun ingredienListtSetter(steps: List<Step?>?): ArrayList<Ingredient> {
        val ingList = mutableListOf<Ingredient>()

        try {
            steps?.forEach {
                ingList.addAll(it?.ingredients as List<Ingredient>) // create a list of all ingredients
            }
        } catch (e: Exception) {
            println("ERROR: ${e.stackTrace}")
        }
        var ingSet = mutableListOf<Ingredient>()
        for(str in ingList){
            if(!ingSet.contains(str))
                ingSet.add(str)
        }
        return ingSet as ArrayList<Ingredient>

    }*/
}

/*private fun <E> MutableList<E>.addAll(elements: List<E?>?) {
    this.addAll(elements)
}*/
