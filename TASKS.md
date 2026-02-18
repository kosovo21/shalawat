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

## Phase 2: Data Layer
- [ ] Create `ShalawatEntity.kt` — Room entity with all columns (id, title, arabicText, transliteration, translation, audioFileName, audioSource, createdAt, updatedAt)
- [ ] Create `ShalawatDao.kt` — DAO interface with `getAllShalawat()`, `getShalawatById()`, `insert()`, `update()`, `delete()`
- [ ] Create `ShalawatDatabase.kt` — Room database class with entity registration and database builder
- [ ] Create `AudioFileManager.kt` — handles copy/delete of user-added audio files in `context.filesDir/audio/`
- [ ] Create `ShalawatRepositoryImpl.kt` — implements `ShalawatRepository` interface, delegates to DAO and AudioFileManager

---

## Phase 3: Domain Layer
- [ ] Create `Shalawat.kt` — clean domain model (no Room annotations)
- [ ] Create `ShalawatRepository.kt` — repository interface (contract)
- [ ] Create `GetAllShalawatUseCase.kt`
- [ ] Create `GetShalawatByIdUseCase.kt`
- [ ] Create `SaveShalawatUseCase.kt` (handles both insert and update)
- [ ] Create `DeleteShalawatUseCase.kt`
- [ ] Add entity ↔ domain model mappers (extension functions)

---

## Phase 4: Dependency Injection (Hilt Modules)
- [ ] Create `DatabaseModule.kt` — provides `ShalawatDatabase` and `ShalawatDao`
- [ ] Create `RepositoryModule.kt` — binds `ShalawatRepositoryImpl` to `ShalawatRepository`
- [ ] Create `PlayerModule.kt` — provides `ExoPlayer` instance

---

## Phase 5: Splash Screen
- [ ] Add splash screen theme in `themes.xml` using AndroidX SplashScreen API
- [ ] Create `SplashScreen.kt` — Compose screen with logo, app name, intro sound
- [ ] Add intro sound file to `assets/audio/` (or `res/raw/`)
- [ ] Implement ~2s delay then navigate to HomeScreen

---

## Phase 6: Navigation
- [ ] Create `AppNavGraph.kt` — define `NavHost` with type-safe routes:
  - `splash` → `home` → `detail/{id}` → `addEdit/{id?}`
- [ ] Set up `MainActivity.kt` as single-activity host with `NavHost`

---

## Phase 7: Home Screen (List)
- [ ] Create `HomeViewModel.kt` — exposes `StateFlow<List<Shalawat>>`, calls `GetAllShalawatUseCase`
- [ ] Create `HomeScreen.kt` — `LazyColumn` listing shalawat titles
  - [ ] FAB for navigating to AddEditScreen (create mode)
  - [ ] Tap item → navigate to DetailScreen
  - [ ] Swipe-to-dismiss for delete with confirmation dialog

---

## Phase 8: Detail Screen + Audio Player
- [ ] Create `DetailViewModel.kt` — loads shalawat by ID, manages ExoPlayer lifecycle
- [ ] Create `DetailScreen.kt`:
  - [ ] Display Arabic text, transliteration, translation
  - [ ] Audio player controls: Play/Pause, Stop, SeekBar, position/duration
  - [ ] Edit button → navigate to AddEditScreen (edit mode)
  - [ ] Delete button with confirmation dialog

---

## Phase 9: Add/Edit Screen (CRUD Form)
- [ ] Create `AddEditViewModel.kt` — handles form state, save/update logic via `SaveShalawatUseCase`
- [ ] Create `AddEditScreen.kt`:
  - [ ] Input fields: title, arabicText, transliteration, translation
  - [ ] Audio file picker using `ActivityResultContracts.GetContent`
  - [ ] Audio file copy to internal storage via `AudioFileManager`
  - [ ] Save button → insert or update, navigate back
  - [ ] Pre-fill fields when in edit mode

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
