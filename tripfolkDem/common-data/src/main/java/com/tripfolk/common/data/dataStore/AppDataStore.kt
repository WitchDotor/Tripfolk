package com.tripfolk.common.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.tripfolk.common.data.dataStore.entities.EnumEntity
import com.tripfolk.common.data.dataStore.entities.PrimitiveEntity
import com.tripfolk.common.data.dataStore.models.DarkModeData
import com.tripfolk.common.data.dataStore.models.LanguageCodesData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(STORE_NAME)
    private val Context.persistDataStore by preferencesDataStore(PERSIST_STORE_NAME)

    val profileStatus = PrimitiveEntity(
        initialValue = "",
        keyName = KeysNames.PROFILE_STATUS,
        dataStore = context.dataStore
    )
    val onBoarding = PrimitiveEntity(
        initialValue = true,
        keyName = KeysNames.ONBOARDING_STATE,
        dataStore = context.persistDataStore,
    )
    val darkMode = EnumEntity(
        initialValue = DarkModeData.AS_SYSTEM,
        keyName = KeysNames.DARK_MODE,
        dataStore = context.dataStore,
        enumEntities = DarkModeData.entries
    )
    val language = EnumEntity(
        initialValue = LanguageCodesData.getInitialValue(),
        keyName = KeysNames.CURRENT_LANGUAGE,
        dataStore = context.persistDataStore,
        enumEntities = LanguageCodesData.entries
    )


    suspend fun clearStore() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    private companion object {
        private const val STORE_NAME = "data_store"
        private const val PERSIST_STORE_NAME = "persist_data_store"
    }

    private object KeysNames {
        const val PROFILE_STATUS = "profile_status"
        const val ONBOARDING_STATE = "onboarding_state"
        const val CURRENT_LANGUAGE = "current_language"
        const val DARK_MODE = "dark_mode"
    }
}