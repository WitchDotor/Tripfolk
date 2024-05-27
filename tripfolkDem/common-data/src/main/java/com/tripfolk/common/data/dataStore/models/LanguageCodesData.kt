package com.tripfolk.common.data.dataStore.models

import java.util.Locale

enum class LanguageCodesData {
    RU,
    EN,
    FR,
    HI,
    JA;

    companion object {
        fun getInitialValue(): LanguageCodesData {
            return try {
                LanguageCodesData.valueOf(Locale.getDefault().language.uppercase())
            } catch (e: Exception) {
                EN
            }
        }

    }
}