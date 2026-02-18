# Shalawat Player App — Technical Specification

## Overview

A native Android application for browsing, playing, and managing Arabic Islamic recitations (Shalawat). Users can view a list of recitations, read full text with transliteration, play associated audio, and perform full CRUD operations on entries. The app opens with a splash screen accompanied by an intro sound.

---

## Platform & Language

| Item | Decision |
|---|---|
| Language | Kotlin (latest stable) |
| Min SDK | API 26 (Android 8.0) |
| Target/Compile SDK | API 35 (Android 15) |
| Build System | Gradle with Kotlin DSL (build.gradle.kts) |

---

## Architecture

**Pattern:** MVVM + Clean Architecture (3-layer)

```
app/
├── data/           # Room DB, DAOs, Entities, Repository implementations, asset helpers
├── domain/         # Domain models, Repository interfaces, Use Cases
├── presentation/   # ViewModels, Compose UI screens, navigation
└── di/             # Hilt dependency injection modules
```

**State management:** Kotlin StateFlow + Coroutines throughout all layers.

---

## UI Framework

**Jetpack Compose** (latest BOM) — declarative, no XML layouts.

### Screens

| Screen | Description |
|---|---|
| Splash | Logo + app name, plays intro sound, transitions to Home after ~2–3s |
| Home / List | Scrollable list of shalawat titles using `LazyColumn` |
| Detail | Full Arabic text, transliteration, translation, and audio player controls |
| Add / Edit | Form screen for creating or updating a shalawat entry |

### Navigation

**Navigation Compose** — single `NavHost` with type-safe routes.

```
SplashScreen → HomeScreen → DetailScreen
                          ↘ AddEditScreen
```

---

## Local Database

**Room 2.6+** (official SQLite abstraction by Google)

Chosen over raw SQLite for: compile-time query validation, Kotlin Coroutines/Flow support, and easy schema migrations.

### Entity: `ShalawatEntity`

| Column | Type | Notes |
|---|---|---|
| id | Int (PK, autoGenerate) | Primary key |
| title | String | Display title |
| arabicText | String | Arabic script content |
| transliteration | String | Latin phonetic text |
| translation | String | Translated meaning |
| audioFileName | String | File name reference (asset or internal storage) |
| audioSource | String | `"asset"` or `"internal"` |
| createdAt | Long | Unix timestamp |
| updatedAt | Long | Unix timestamp |

### DAO Operations

- `getAllShalawat(): Flow<List<ShalawatEntity>>`
- `getShalawatById(id: Int): ShalawatEntity?`
- `insert(shalawat: ShalawatEntity)`
- `update(shalawat: ShalawatEntity)`
- `delete(shalawat: ShalawatEntity)`

---

## Audio / Asset Management

### Pre-bundled Audio (Read-only)

- Location: `assets/audio/`
- Packed inside the APK at build time
- Accessed at runtime via `AssetManager`
- Referenced in DB with `audioSource = "asset"`

### User-added Audio (CRUD)

- Location: `context.filesDir/audio/` (Internal Storage — no permissions needed)
- User selects audio file via `ActivityResultContracts.GetContent` (Storage Access Framework)
- File is copied into internal storage on selection
- Referenced in DB with `audioSource = "internal"`
- Deleted from filesystem when DB entry is deleted

### Audio Playback

**Jetpack Media3 / ExoPlayer** (recommended over raw `MediaPlayer`)

- Supports assets, internal files, and future streaming
- Built-in playback controls, buffering, and error handling
- Optional: `MediaSessionService` for background playback and notification controls
- Player lifecycle tied to `ViewModel` using `DisposableEffect` in Compose

### Player Controls (Detail Screen)

- Play / Pause toggle
- Stop
- Seek bar (progress indicator)
- Current position / Total duration display

---

## Splash Screen

- **AndroidX SplashScreen API** (`androidx.core:core-splashscreen`) for OS-level splash handling
- Custom branding (logo, app name) rendered in the initial activity
- Intro sound played via `MediaPlayer` (short clip, one-shot)
- Minimum display duration set to ~2s before navigating to `HomeScreen`

---

## CRUD Operations

| Operation | Entry Point | Behavior |
|---|---|---|
| Create | FAB on Home screen | Opens `AddEditScreen` in create mode |
| Read | Home list + Detail screen | Read from Room via Flow |
| Update | Edit button on Detail screen | Opens `AddEditScreen` pre-filled |
| Delete | Detail screen or swipe-to-dismiss on list | Confirmation dialog, deletes DB record + audio file if internal |

---

## Dependency Injection

**Hilt (Dagger Hilt)** — standard DI for Android.

Modules provided:
- `DatabaseModule` — provides `ShalawatDatabase`, `ShalawatDao`
- `RepositoryModule` — binds `ShalawatRepositoryImpl` to `ShalawatRepository`
- `PlayerModule` — provides `ExoPlayer` instance scoped appropriately

---

## Full Dependency Stack

```toml
# libs.versions.toml (Version Catalog)

[versions]
kotlin = "2.x.x"                     # latest stable
compose-bom = "latest"
room = "2.6.x"
media3 = "1.x.x"
hilt = "2.x.x"
navigation-compose = "latest"
splashscreen = "1.0.x"
coroutines = "1.x.x"

[libraries]
# Compose
compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "compose-bom" }
compose-ui = { group = "androidx.compose.ui", name = "ui" }
compose-material3 = { group = "androidx.compose.material3", name = "material3" }
compose-tooling = { group = "androidx.compose.ui", name = "ui-tooling-preview" }

# Navigation
navigation-compose = { group = "androidx.navigation", name = "navigation-compose" }

# Room
room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }
room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }  # kapt/ksp

# Media3 / ExoPlayer
media3-exoplayer = { group = "androidx.media3", name = "media3-exoplayer", version.ref = "media3" }
media3-ui = { group = "androidx.media3", name = "media3-ui", version.ref = "media3" }
media3-session = { group = "androidx.media3", name = "media3-session", version.ref = "media3" }

# Hilt
hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
hilt-compiler = { group = "com.google.dagger", name = "hilt-android-compiler", version.ref = "hilt" }
hilt-navigation-compose = { group = "androidx.hilt", name = "hilt-navigation-compose" }

# Splash Screen
splashscreen = { group = "androidx.core", name = "core-splashscreen", version.ref = "splashscreen" }

# Coroutines
coroutines-android = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-android" }
```

> Always resolve exact latest stable versions from [developer.android.com](https://developer.android.com) and [maven.google.com](https://maven.google.com) at project setup time.

---

## Annotation Processing

Use **KSP (Kotlin Symbol Processing)** over KAPT for Room and Hilt — faster build times, officially recommended.

---

## Permissions

| Permission | When Required |
|---|---|
| None required for bundled assets | Pre-bundled audio in `assets/` needs no permissions |
| `READ_EXTERNAL_STORAGE` / SAF | Not needed — use Storage Access Framework (`GetContent`) for user file picking |
| `POST_NOTIFICATIONS` (API 33+) | Required only if implementing background playback notification |
| `FOREGROUND_SERVICE` | Required only if implementing background playback via `MediaSessionService` |

---

## Data Seeding

On first app launch (detected via `SharedPreferences` or a `Room` migration callback), seed the database with a default set of pre-bundled shalawat entries pointing to files in `assets/audio/`.

---

## Project File Structure

```
com.example.shalawat/
├── ShalawatApplication.kt          # Hilt application class
├── MainActivity.kt                 # Single activity, hosts NavHost
│
├── data/
│   ├── local/
│   │   ├── ShalawatDatabase.kt
│   │   ├── ShalawatDao.kt
│   │   └── ShalawatEntity.kt
│   ├── repository/
│   │   └── ShalawatRepositoryImpl.kt
│   └── audio/
│       └── AudioFileManager.kt     # handles copy/delete of audio files
│
├── domain/
│   ├── model/
│   │   └── Shalawat.kt             # clean domain model (no Room annotations)
│   ├── repository/
│   │   └── ShalawatRepository.kt   # interface
│   └── usecase/
│       ├── GetAllShalawatUseCase.kt
│       ├── GetShalawatByIdUseCase.kt
│       ├── SaveShalawatUseCase.kt
│       └── DeleteShalawatUseCase.kt
│
├── presentation/
│   ├── navigation/
│   │   └── AppNavGraph.kt
│   ├── splash/
│   │   └── SplashScreen.kt
│   ├── home/
│   │   ├── HomeScreen.kt
│   │   └── HomeViewModel.kt
│   ├── detail/
│   │   ├── DetailScreen.kt
│   │   └── DetailViewModel.kt
│   └── addedit/
│       ├── AddEditScreen.kt
│       └── AddEditViewModel.kt
│
└── di/
    ├── DatabaseModule.kt
    ├── RepositoryModule.kt
    └── PlayerModule.kt
```

---

## Key Design Decisions Summary

| Concern | Choice | Reason |
|---|---|---|
| Language | Kotlin | Modern, concise, full Jetpack support |
| UI | Jetpack Compose | Latest standard, less boilerplate than XML |
| Database | Room | Type-safe SQLite abstraction, Flow support |
| Audio | Media3 / ExoPlayer | Robust, supports background play, maintained by Google |
| DI | Hilt | Standard Android DI, integrates with Compose nav |
| Annotation Processing | KSP | Faster builds than KAPT |
| User file picking | SAF (GetContent) | No storage permissions needed |
| Pre-bundled audio | assets/ folder | Packaged in APK, no permissions needed |
| Async | Coroutines + StateFlow | Kotlin-native, lifecycle-safe |
| Splash | AndroidX SplashScreen API | OS-integrated, handles edge launch cases |