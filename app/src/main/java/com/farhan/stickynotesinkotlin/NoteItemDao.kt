package com.farhan.stickynotesinkotlin

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteItemDao {
    @Query("SELECT * FROM NoteItem ORDER BY id DESC LIMIT 500")
    fun getAll(): LiveData<List<NoteItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(noteItem: NoteItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplaceAll(noteItems: List<NoteItem>)

    @Delete
    fun delete(noteItem: NoteItem)

    @Update
    fun update(noteItem: NoteItem)

    @Query("DELETE FROM NoteItem")
    fun deleteAll()
}
