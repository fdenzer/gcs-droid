package com.example.gurpscharactersheetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gurpscharactersheetapp.ui.theme.GurpsCharacterSheetAppTheme
import com.example.charactersheet.CharacterParser
import com.example.charactersheet.CharacterSheet
import com.example.charactersheet.ui.CharacterSheetScreen
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GurpsCharacterSheetAppTheme {
                var characterSheet: CharacterSheet? by remember { mutableStateOf(null) }
                var isLoading: Boolean by remember { mutableStateOf(true) }
                var error: String? by remember { mutableStateOf(null) }

                LaunchedEffect(Unit) {
                    try {
                        // Access an asset from the charactersheet module
                        // Note: The asset path needs to be relative to the charactersheet module's assets folder
                        // However, when accessed from the app module, it's typically just the filename
                        // if the charactersheet module correctly packages its assets.
                        // For direct asset manager access from app to library, it might be more complex.
                        // A simpler way for library assets is to have the library expose a loading function.

                        // Let's assume CharacterParser can take context if needed, or we load it here.
                        // For now, we'll try opening the asset directly.
                        // This relies on the charactersheet module's assets being merged into the app's assets.
                        assets.open("Dai_Blackthorn.gcs").use { inputStream ->
                            characterSheet = CharacterParser.parse(inputStream)
                            if (characterSheet == null) {
                                error = "Failed to parse character sheet."
                            }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                        error = "Error loading character sheet: ${e.message}"
                    } finally {
                        isLoading = false
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when {
                        isLoading -> LoadingScreen()
                        error != null -> ErrorScreen(error!!)
                        characterSheet != null -> CharacterSheetScreen(characterSheet = characterSheet!!)
                        else -> ErrorScreen("Character sheet data is unavailable.")
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
        Text(
            text = message,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GurpsCharacterSheetAppTheme {
        // You could show a specific state for preview, e.g., ErrorScreen
        ErrorScreen("Preview Error Message")
    }
}
