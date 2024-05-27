package com.tripfolk.common.data.dataStore.entities

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

open class PrimitiveEntity<T>(
    private val initialValue: T,
    private val keyName: String,
    private val dataStore: DataStore<Preferences>,
) : Entity<T> {

    override suspend fun editValue(newValue: T) {
        dataStore.edit { pref ->
            when (newValue) {
                is String -> pref[stringPreferencesKey(keyName)] = newValue
                is Int -> pref[intPreferencesKey(keyName)] = newValue
                is Boolean -> pref[booleanPreferencesKey(keyName)] = newValue
                else -> {
                    throw IllegalArgumentException(" ${this@PrimitiveEntity.javaClass.name} Unsupported type of $initialValue")
                }
            }
        }
    }

    override fun getValueFlow(): Flow<T> {
        return dataStore.data.map { pref ->
            when (initialValue) {
                is String -> pref[stringPreferencesKey(keyName)] ?: initialValue
                is Int -> pref[intPreferencesKey(keyName)] ?: initialValue
                is Boolean -> pref[booleanPreferencesKey(keyName)] ?: initialValue
                else -> {
                    throw IllegalArgumentException(" ${this@PrimitiveEntity.javaClass.name} Unsupported type of $initialValue")
                }
            } as T
        }
    }

    override fun getValue(): T {
        return runBlocking { getValueFlow().first() }
    }
}





