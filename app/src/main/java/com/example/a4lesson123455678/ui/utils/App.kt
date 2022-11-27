package com.example.a4lesson123455678.ui.utils

import android.app.Application
import android.content.SharedPreferences
import com.example.a4lesson123455678.data.NoteDatabase

class App: Application() {
    private lateinit var preferences: SharedPreferences
    companion object{
        lateinit var db: NoteDatabase
        lateinit var prefs:Prefs
    }

    override fun onCreate() {
        super.onCreate()
        db = NoteDatabase.getDbInstance(this)
        preferences = this.applicationContext
            .getSharedPreferences("settings", MODE_PRIVATE)
        prefs = Prefs(preferences)
    }
}