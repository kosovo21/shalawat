# Shalawat Player App — Task Breakdown

## Phase 1: Project Initialization & Setup ✅
- [x] Create new Android project (Kotlin, min SDK 26, target SDK 35, Compose activity template)
- [x] Configure `libs.versions.toml` with all dependency versions (Compose BOM, Room, Media3, Hilt, Navigation, Splashscreen, Coroutines)
- [x] Configure `build.gradle.kts` (project-level) — Hilt plugin, KSP plugin
- [x] Configure `build.gradle.kts` (app-level) — apply plugins (KSP, Hilt), add all dependencies
- [x] Set up package structure: `data/`, `domain/`, `presentation/`, `di/`
- [x] Create `ShalawatApplication.kt` with `@HiltAndroidApp`
- [x] Update `AndroidManifest.xml` with application class reference

---

## Phase 2: Data Layer ✅
- [x] Create `ShalawatEntity.kt` — Room entity with all columns (id, title, arabicText, transliteration, translation, audioFileName, audioSource, createdAt, updatedAt)
- [x] Create `ShalawatDao.kt` — DAO interface with `getAllShalawat()`, `getShalawatById()`, `insert()`, `update()`, `delete()`
- [x] Create `ShalawatDatabase.kt` — Room database class with entity registration and database builder
- [x] Create `AudioFileManager.kt` — handles copy/delete of user-added audio files in `context.filesDir/audio/`
- [x] Create `ShalawatRepositoryImpl.kt` — implements `ShalawatRepository` interface, delegates to DAO and AudioFileManager

---

## Phase 3: Domain Layer ✅
- [x] Create `Shalawat.kt` — clean domain model (no Room annotations)
- [x] Create `ShalawatRepository.kt` — repository interface (contract)
- [x] Create `GetAllShalawatUseCase.kt`
- [x] Create `GetShalawatByIdUseCase.kt`
- [x] Create `SaveShalawatUseCase.kt` (handles both insert and update)
- [x] Create `DeleteShalawatUseCase.kt`
- [x] Add entity ↔ domain model mappers (extension functions)

---

## Phase 4: Dependency Injection (Hilt Modules) ✅
- [x] Create `DatabaseModule.kt` — provides `ShalawatDatabase` and `ShalawatDao`
- [x] Create `RepositoryModule.kt` — binds `ShalawatRepositoryImpl` to `ShalawatRepository`
- [x] Create `PlayerModule.kt` — provides `ExoPlayer` instance

---

## Phase 5: Splash Screen ✅
- [x] Add splash screen theme in `themes.xml` using AndroidX SplashScreen API
- [x] Create `SplashScreen.kt` — Compose screen with logo, app name, intro sound
- [x] Add intro sound file to `assets/audio/` (or `res/raw/`)
- [x] Implement ~2s delay then navigate to HomeScreen

---

## Phase 6: Navigation ✅
- [x] Create `AppNavGraph.kt` — define `NavHost` with type-safe routes:
  - `splash` → `home` → `detail/{id}` → `addEdit/{id?}`
- [x] Set up `MainActivity.kt` as single-activity host with `NavHost`

---

## Phase 7: Home Screen (List) ✅
- [x] Create `HomeViewModel.kt` — exposes `StateFlow<List<Shalawat>>`, calls `GetAllShalawatUseCase`
- [x] Create `HomeScreen.kt` — `LazyColumn` listing shalawat titles
  - [x] FAB for navigating to AddEditScreen (create mode)
  - [x] Tap item → navigate to DetailScreen
  - [x] Swipe-to-dismiss for delete with confirmation dialog

---

## Phase 8: Detail Screen + Audio Player ✅
- [x] Create `DetailViewModel.kt` — loads shalawat by ID, manages ExoPlayer lifecycle
- [x] Create `DetailScreen.kt`:
  - [x] Display Arabic text, transliteration, translation
  - [x] Audio player controls: Play/Pause, Stop, SeekBar, position/duration
  - [x] Edit button → navigate to AddEditScreen (edit mode)
  - [x] Delete button with confirmation dialog

---

## Phase 9: Add/Edit Screen (CRUD Form) ✅
- [x] Create `AddEditViewModel.kt` — handles form state, save/update logic via `SaveShalawatUseCase`
- [x] Create `AddEditScreen.kt`:
  - [x] Input fields: title, arabicText, transliteration, translation
  - [x] Audio file picker using `ActivityResultContracts.GetContent`
  - [x] Audio file copy to internal storage via `AudioFileManager`
  - [x] Save button → insert or update, navigate back
  - [x] Pre-fill fields when in edit mode

---

## Phase 10: Data Seeding
- [ ] Add pre-bundled audio files to `assets/audio/`
- [ ] Implement first-launch detection (SharedPreferences or Room callback)
- [ ] Seed default shalawat entries into Room database on first launch

---

## Phase 11: Polish & Final Integration
- [ ] Ensure delete also removes audio file from internal storage (if `audioSource == "internal"`)
- [ ] Add proper error handling and loading states across all screens
- [ ] Tie ExoPlayer lifecycle to ViewModel with `DisposableEffect`
- [ ] Test complete navigation flow: Splash → Home → Detail → AddEdit
- [ ] Test CRUD: create, read, update, delete entries
- [ ] Test audio playback for both asset and internal audio sources
- [ ] Final UI polish: typography, colors, Material 3 theming
