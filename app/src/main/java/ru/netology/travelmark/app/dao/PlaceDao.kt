package ru.netology.travelmark.app.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.netology.travelmark.app.entity.PlaceEntity

@Dao
    interface PlaceDao {
        @Query("SELECT * FROM PlaceEntity ORDER BY id ASC")
        fun getAll(): Flow<List<PlaceEntity>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(place: PlaceEntity)

        @Query("DELETE FROM PlaceEntity WHERE id = :id")
        suspend fun removeById(id: Long)

    }