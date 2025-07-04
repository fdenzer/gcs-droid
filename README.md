# GURPS Character Sheet Android Viewer (MVP)

This is a Minimum Viable Product (MVP) Android application designed to display GURPS character sheets (`.gcs` files). It's built using Kotlin and Jetpack Compose, with a focus on a modern, feature-module-based architecture.

## Features

*   Parses `.gcs` (JSON format) character sheet files.
*   Displays key character information including:
    *   Profile (Name, Age, Appearance, etc.)
    *   Attributes (ST, DX, IQ, HT, etc. with values and costs)
    *   Traits (Advantages, Disadvantages, Perks, Quirks - including nested traits and basic modifier info)
    *   Skills (Name, Level, Points, Difficulty)
*   Basic loading and error states.
*   Structured with a main application module (`app`) and a feature module (`charactersheet`) for character sheet logic and UI.

## Current Scope

This application currently loads and displays a single, hardcoded character sheet: `Dai Blackthorn.gcs`, which is included in the assets of the `charactersheet` module.

Complex data within traits or skills (like detailed features, weapon stats, or skill defaults) are currently displayed as raw JSON strings or summarized.

## Project Structure

*   `GurpsCharacterSheetApp/`: Root directory for the Android project.
    *   `app/`: The main application module. Contains `MainActivity` which integrates and launches the character sheet display.
    *   `charactersheet/`: An Android library module (feature module) responsible for:
        *   Data models for the `.gcs` file (`CharacterModels.kt`).
        *   Parsing logic for the `.gcs` file (`CharacterParser.kt`).
        *   Jetpack Compose UI components for displaying the character sheet (`ui/CharacterSheetScreen.kt`).
        *   The sample `Dai_Blackthorn.gcs` file is located in `charactersheet/src/main/assets/`.

## Building and Running

1.  Open the `GurpsCharacterSheetApp` directory in Android Studio.
2.  Let Gradle sync and build the project.
3.  Run the `app` configuration on an Android emulator or a physical device.

## Key Technologies Used

*   Kotlin
*   Jetpack Compose for the UI
*   `kotlinx.serialization` for JSON parsing
*   Android App/Library Modules

## Future Enhancements (Post-MVP)

*   File picker to allow users to select and load any `.gcs` file from their device.
*   More detailed display for complex trait features, weapon statistics, and skill defaults.
*   Display of character portrait (decode Base64 image).
*   Display of calculated values (Basic Lift, Move, Dodge, Swing/Thrust damage).
*   UI for hit locations and other settings.
*   Improved error handling and user feedback.
*   General UI/UX polish and theming enhancements.
