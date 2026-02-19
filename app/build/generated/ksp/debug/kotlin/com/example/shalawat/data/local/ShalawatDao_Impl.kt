package com.example.shalawat.`data`.local

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ShalawatDao_Impl(
  __db: RoomDatabase,
) : ShalawatDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfShalawatEntity: EntityInsertAdapter<ShalawatEntity>

  private val __deleteAdapterOfShalawatEntity: EntityDeleteOrUpdateAdapter<ShalawatEntity>

  private val __updateAdapterOfShalawatEntity: EntityDeleteOrUpdateAdapter<ShalawatEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfShalawatEntity = object : EntityInsertAdapter<ShalawatEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `shalawat` (`id`,`title`,`arabicText`,`transliteration`,`translation`,`audioFileName`,`audioSource`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ShalawatEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.arabicText)
        statement.bindText(4, entity.transliteration)
        statement.bindText(5, entity.translation)
        statement.bindText(6, entity.audioFileName)
        statement.bindText(7, entity.audioSource)
        statement.bindLong(8, entity.createdAt)
        statement.bindLong(9, entity.updatedAt)
      }
    }
    this.__deleteAdapterOfShalawatEntity = object : EntityDeleteOrUpdateAdapter<ShalawatEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `shalawat` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ShalawatEntity) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfShalawatEntity = object : EntityDeleteOrUpdateAdapter<ShalawatEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `shalawat` SET `id` = ?,`title` = ?,`arabicText` = ?,`transliteration` = ?,`translation` = ?,`audioFileName` = ?,`audioSource` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ShalawatEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.arabicText)
        statement.bindText(4, entity.transliteration)
        statement.bindText(5, entity.translation)
        statement.bindText(6, entity.audioFileName)
        statement.bindText(7, entity.audioSource)
        statement.bindLong(8, entity.createdAt)
        statement.bindLong(9, entity.updatedAt)
        statement.bindLong(10, entity.id.toLong())
      }
    }
  }

  public override suspend fun insert(shalawat: ShalawatEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfShalawatEntity.insert(_connection, shalawat)
  }

  public override suspend fun delete(shalawat: ShalawatEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfShalawatEntity.handle(_connection, shalawat)
  }

  public override suspend fun update(shalawat: ShalawatEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfShalawatEntity.handle(_connection, shalawat)
  }

  public override fun getAllShalawat(): Flow<List<ShalawatEntity>> {
    val _sql: String = "SELECT * FROM shalawat ORDER BY createdAt DESC"
    return createFlow(__db, false, arrayOf("shalawat")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArabicText: Int = getColumnIndexOrThrow(_stmt, "arabicText")
        val _columnIndexOfTransliteration: Int = getColumnIndexOrThrow(_stmt, "transliteration")
        val _columnIndexOfTranslation: Int = getColumnIndexOrThrow(_stmt, "translation")
        val _columnIndexOfAudioFileName: Int = getColumnIndexOrThrow(_stmt, "audioFileName")
        val _columnIndexOfAudioSource: Int = getColumnIndexOrThrow(_stmt, "audioSource")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _result: MutableList<ShalawatEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ShalawatEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArabicText: String
          _tmpArabicText = _stmt.getText(_columnIndexOfArabicText)
          val _tmpTransliteration: String
          _tmpTransliteration = _stmt.getText(_columnIndexOfTransliteration)
          val _tmpTranslation: String
          _tmpTranslation = _stmt.getText(_columnIndexOfTranslation)
          val _tmpAudioFileName: String
          _tmpAudioFileName = _stmt.getText(_columnIndexOfAudioFileName)
          val _tmpAudioSource: String
          _tmpAudioSource = _stmt.getText(_columnIndexOfAudioSource)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = ShalawatEntity(_tmpId,_tmpTitle,_tmpArabicText,_tmpTransliteration,_tmpTranslation,_tmpAudioFileName,_tmpAudioSource,_tmpCreatedAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getShalawatById(id: Int): ShalawatEntity? {
    val _sql: String = "SELECT * FROM shalawat WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArabicText: Int = getColumnIndexOrThrow(_stmt, "arabicText")
        val _columnIndexOfTransliteration: Int = getColumnIndexOrThrow(_stmt, "transliteration")
        val _columnIndexOfTranslation: Int = getColumnIndexOrThrow(_stmt, "translation")
        val _columnIndexOfAudioFileName: Int = getColumnIndexOrThrow(_stmt, "audioFileName")
        val _columnIndexOfAudioSource: Int = getColumnIndexOrThrow(_stmt, "audioSource")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _result: ShalawatEntity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArabicText: String
          _tmpArabicText = _stmt.getText(_columnIndexOfArabicText)
          val _tmpTransliteration: String
          _tmpTransliteration = _stmt.getText(_columnIndexOfTransliteration)
          val _tmpTranslation: String
          _tmpTranslation = _stmt.getText(_columnIndexOfTranslation)
          val _tmpAudioFileName: String
          _tmpAudioFileName = _stmt.getText(_columnIndexOfAudioFileName)
          val _tmpAudioSource: String
          _tmpAudioSource = _stmt.getText(_columnIndexOfAudioSource)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _result = ShalawatEntity(_tmpId,_tmpTitle,_tmpArabicText,_tmpTransliteration,_tmpTranslation,_tmpAudioFileName,_tmpAudioSource,_tmpCreatedAt,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
