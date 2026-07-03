package com.example.pdm_00097524.RankeUca.data.session

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class SessionManager(private val context: Context) {

    companion object {
        private val NAME_KEY = stringPreferencesKey("user_name")
        private  val CARNET_KEY = stringPreferencesKey("user_carnet")
        private val API_KEY = stringPreferencesKey("api_key")
    }

    val name: Flow<String?> = context.dataStore.data.map { it[NAME_KEY] }
    val carnet: Flow<String?> = context.dataStore.data.map { it[CARNET_KEY] }
    val apikey: Flow<String?> = context.dataStore.data.map { it[API_KEY] }

    suspend fun save(apikey: String, name: String, carnet: String){
        context.dataStore.edit { prefs ->
            prefs[API_KEY] = apikey
            prefs[NAME_KEY] = name
            prefs[CARNET_KEY] = carnet
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }

}