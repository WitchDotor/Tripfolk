package com.tripfolk.common.data.dataStore.entities

import kotlinx.coroutines.flow.Flow

interface Entity<T> {

     suspend fun editValue(newValue: T)

     fun getValueFlow(): Flow<T>

     fun getValue(): T
}