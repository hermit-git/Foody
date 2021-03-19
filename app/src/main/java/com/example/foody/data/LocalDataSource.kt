package com.example.foody.data

import com.example.foody.data.database.RecipesDao
import com.example.foody.data.database.entities.FavoritesEntity
import com.example.foody.data.database.entities.FoodJokeEntity
import com.example.foody.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
        private val recipesDao: RecipesDao
) {

    fun readRecipes():Flow<List<RecipesEntity>>{
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity){
        recipesDao.insertRecipes(recipesEntity)
    }

    fun readFavoritesRecipes():Flow<List<FavoritesEntity>> {
        return recipesDao.readFavoritesRecipe()
    }

    suspend fun insertFavoritesRecipe(favoritesEntity: FavoritesEntity){
        recipesDao.insertFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity){
        recipesDao.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteAllFavoritesRecipes(){
        recipesDao.deleteAllFavoritesRecipes()
    }

    fun readFoodJoke():Flow<List<FoodJokeEntity>> = recipesDao.readFoodJoke()

    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity) = recipesDao.insertFoodJoke(foodJokeEntity)
}