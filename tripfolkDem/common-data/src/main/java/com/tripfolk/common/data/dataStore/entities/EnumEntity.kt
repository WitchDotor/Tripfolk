package com.tripfolk.common.data.dataStore.entities

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlin.enums.EnumEntries

class EnumEntity<T : Enum<T>>(
    private val initialValue: T,
    keyName: String,
    dataStore: DataStore<Preferences>,
    private val enumEntities: EnumEntries<T>
) : Entity<T> {

    private val stringEntity =
        PrimitiveEntity(initialValue = initialValue.name, keyName = keyName, dataStore = dataStore)

    override suspend fun editValue(newValue: T) {
        stringEntity.editValue(newValue.name)
    }

    override fun getValue(): T {
        return runBlocking { getValueFlow().first() }
    }

    override fun getValueFlow(): Flow<T> {
        return stringEntity.getValueFlow().map { name ->
            findValue(name) ?: initialValue
        }
    }

    private fun findValue(value: String?) = enumEntities.find { enum -> value == enum.name }
}