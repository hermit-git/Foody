package com.example.foody.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import com.example.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.foody.util.Constants.Companion.PREFERENCES_BACK_ONLINE
import com.example.foody.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.example.foody.util.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.example.foody.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.example.foody.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.example.foody.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import com.example.foody.util.Constants.Companion.DEFAULT_DIET_TYPE as DEFAULT_DIET_TYPE

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKey{
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
        val backOnline = booleanPreferencesKey(PREFERENCES_BACK_ONLINE)
    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(name = PREFERENCES_NAME)

    suspend fun saveMealAndDietType(mealType:String, mealTypeId:Int, dietType:String, dietTypeId:Int){
        dataStore.edit{ preferences ->
            preferences[PreferenceKey.selectedMealType] = mealType
            preferences[PreferenceKey.selectedMealTypeId] = mealTypeId
            preferences[PreferenceKey.selectedDietType] = dietType
            preferences[PreferenceKey.selectedDietTypeId] = dietTypeId
        }
    }

    suspend fun saveBackOnline(backOnline:Boolean){
        dataStore.edit { preferences ->
            preferences[PreferenceKey.backOnline] = backOnline
        }
    }

    val readMealAndDietType: Flow<MealAndDietType> = dataStore.data
            .catch { exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val selectedMealType = preferences[PreferenceKey.selectedDietType] ?: DEFAULT_MEAL_TYPE
                val selectedMealTypeId = preferences[PreferenceKey.selectedMealTypeId] ?: 0
                val selectedDietType = preferences[PreferenceKey.selectedDietType] ?: DEFAULT_DIET_TYPE
                val selectedDietTypeId = preferences[PreferenceKey.selectedDietTypeId] ?: 0

                MealAndDietType(selectedMealType,selectedMealTypeId,selectedDietType,selectedDietTypeId)

            }
    val readBackOnline : Flow<Boolean> = dataStore.data
            .catch { exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val backOnline = preferences[PreferenceKey.backOnline] ?: false
                backOnline
            }
}


data class MealAndDietType(
        val selectedMealType:String,
        val selectedMealTypeId:Int,
        val selectedDietType:String,
        val selectedDietTypeId: Int
)

