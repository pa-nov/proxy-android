package com.panov.proxy.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocaleManager {
    fun Context.applyLocaleFromSettings(): Context {
        val language = Settings(this).getLoadedData(
            Settings.General.LANGUAGE, Settings.languages[0]
        )
        return if (language.isNotBlank()) this.applyLocale(language) else this
    }

    fun Context.applyLocale(language: String): Context {
        val locale = Locale.forLanguageTag(language)
        val config = Configuration(this.resources.configuration)
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        return this.createConfigurationContext(config)
    }

    fun getDisplayLanguage(language: String, locale: String = language): String {
        return Locale.forLanguageTag(language).getDisplayLanguage(
            Locale.forLanguageTag(locale)
        ).replaceFirstChar {
            it.uppercase()
        }
    }
}