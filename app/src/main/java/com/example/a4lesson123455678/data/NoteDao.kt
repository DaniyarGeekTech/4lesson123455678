package com.example.a4lesson123455678.data

import androidx.room.*
import com.example.a4lesson123455678.model.NoteModel


@Dao
interface NoteDao {

    @Query("SELECT* FROM notemodel")
    fun getAllNote(): List<NoteModel>

    @Insert
    fun addNote(model: NoteModel)

    @Delete
    fun deleteNote(model: NoteModel)

    @Update
    fun updateNote(model: NoteModel)
}