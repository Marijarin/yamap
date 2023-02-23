package ru.netology.travelmark.app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.netology.travelmark.app.dao.PlaceDao
import ru.netology.travelmark.app.entity.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1)
abstract class PlaceDb: RoomDatabase(){
    abstract fun placeDao(): PlaceDao

    companion object {
        @Volatile
        private var instance: PlaceDb? = null

        fun getInstance(context: Context): PlaceDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, PlaceDb::class.java, "place.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}