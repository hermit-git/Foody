package com.example.foody.data.models

import com.google.gson.annotations.SerializedName

data class FoodJoke(
        @SerializedName("text")
        val text: String
)