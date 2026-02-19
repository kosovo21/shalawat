package com.example.shalawat.`data`.local

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ShalawatDatabase_Impl : ShalawatDatabase() {
  private val _shalawatDao: Lazy<ShalawatDao> = lazy {
    ShalawatDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "756fb0279bb91be8c207d87c92dad314", "86737b1d34ac4fcda7f6a984804fd525") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `shalawat` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `arabicText` TEXT NOT NULL, `transliteration` TEXT NOT NULL, `translation` TEXT NOT NULL, `audioFileName` TEXT NOT NULL, `audioSource` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '756fb0279bb91be8c207d87c92dad314')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `shalawat`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsShalawat: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsShalawat.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShalawat.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShalawat.put("arabicText", TableInfo.Column("arabicText", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShalawat.put("transliteration", TableInfo.Column("transliteration", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShalawat.put("translation", TableInfo.Column("translation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShalawat.put("audioFileName", TableInfo.Column("audioFileName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShalawat.put("audioSource", TableInfo.Column("audioSource", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShalawat.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsShalawat.put("updatedAt", TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysShalawat: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesShalawat: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoShalawat: TableInfo = TableInfo("shalawat", _columnsShalawat, _foreignKeysShalawat, _indicesShalawat)
        val _existingShalawat: TableInfo = read(connection, "shalawat")
        if (!_infoShalawat.equals(_existingShalawat)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |shalawat(com.example.shalawat.data.local.ShalawatEntity).
              | Expected:
              |""".trimMargin() + _infoShalawat + """
              |
              | Found:
              |""".trimMargin() + _existingShalawat)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "shalawat")
  }

  public override fun clearAllTables() {
    super.performClear(false, "shalawat")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(ShalawatDao::class, ShalawatDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun shalawatDao(): ShalawatDao = _shalawatDao.value
}
