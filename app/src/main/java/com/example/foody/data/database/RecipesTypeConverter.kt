package com.example.foody.data.database

import androidx.room.TypeConverter
import com.example.foody.data.models.FoodRecipe
import com.example.foody.data.models.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe):String = gson.toJson(foodRecipe)

    @TypeConverter
    fun stringToFoodRecipe(data:String):FoodRecipe {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data,listType)
    }

    @TypeConverter
    fun resultToString(result: Result):String = gson.toJson(result)

    @TypeConverter
    fun stringToResult(data: String):Result{
        val listType = object: TypeToken<Result>() {}.type
        return gson.fromJson(data,listType)
    }

}