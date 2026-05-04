package com.panov.proxy.utils

import android.content.Context
import android.content.res.Configuration
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.Locale

object LocaleManager {
    fun Context.applyLocaleFromSettings(): Context {
        val language = runBlocking {
            Settings(this@applyLocaleFromSettings).getData(
                Settings.General.LANGUAGE, Settings.languages[0]
            ).first()
        }
        if (language.isNotBlank()) {
            val locale = Locale.forLanguageTag(language)
            val config = Configuration(this.resources.configuration)
            config.setLocale(locale)
            config.setLayoutDirection(locale)
            return this.createConfigurationContext(config)
        } else {
            return this
        }
    }

    fun getDisplayLanguage(language: String, locale: String = language): String {
        return Locale.forLanguageTag(language).getDisplayLanguage(
            Locale.forLanguageTag(locale)
        ).replaceFirstChar {
            it.uppercase()
        }
    }
}