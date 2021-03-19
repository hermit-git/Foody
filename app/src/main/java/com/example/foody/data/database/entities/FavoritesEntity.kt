package com.example.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.data.models.Result
import com.example.foody.util.Constants.Companion.FAVORITES_RECIPES_TABLE


@Entity(tableName = FAVORITES_RECIPES_TABLE)
class FavoritesEntity(
        @PrimaryKey(autoGenerate = true)
        val id:Int,
        var result: Result
)