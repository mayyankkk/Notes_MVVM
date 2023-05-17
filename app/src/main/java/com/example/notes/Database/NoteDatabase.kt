package com.example.notes.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.Models.Note
import com.example.notes.utilities.DATABASE_NAME

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {

    abstract fun getNoteDao():NoteDao

    companion object{
        @Volatile
        private var INTANCE:NoteDatabase?=null
        fun getDatabase(context:Context):NoteDatabase{
            return INTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java ,
                    DATABASE_NAME
                )
                    .build()
                INTANCE=instance
                instance
            }
        }
    }
}