package com.example.charactersheet

import kotlinx.serialization.json.Json
import java.io.InputStream

object CharacterParser {

    private val json = Json {
        ignoreUnknownKeys = true // Be lenient with unknown keys in the JSON
        isLenient = true         // Allows for some minor JSON format deviations
        coerceInputValues = true // Coerces values if possible (e.g. null for missing optional primitive)
    }

    fun parse(inputStream: InputStream): CharacterSheet? {
        return try {
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            json.decodeFromString<CharacterSheet>(jsonString)
        } catch (e: Exception) {
            // Log the error or handle it as needed
            // For now, just print stack trace and return null
            e.printStackTrace()
            null
        }
    }
}
