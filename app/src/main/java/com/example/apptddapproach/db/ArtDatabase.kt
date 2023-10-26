package com.example.apptddapproach.db

import androidx.room.Database

@Database(entities = [Art::class], version = 1)
abstract class ArtDatabase {
    abstract fun artDAO() : ArtDAO
}