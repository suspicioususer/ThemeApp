package com.example.themeapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.themeapp.model.CustomTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(private val context: Context) {

    companion object {
        private val Context.appDataStore: DataStore<Preferences> by preferencesDataStore("preferences")
        val THEME_KEY = intPreferencesKey("theme")
    }

    var theme: Flow<CustomTheme> = context.appDataStore.data.map { pref ->
        CustomTheme.getTheme(pref[THEME_KEY])
    }

    suspend fun saveTheme(theme: CustomTheme) {
        context.appDataStore.edit { prefs ->
            prefs[THEME_KEY] = theme.value
        }
    }
}