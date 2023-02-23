package ru.netology.travelmark.app.repository

import kotlinx.coroutines.flow.Flow
import ru.netology.travelmark.app.dto.Place

interface PlacesRepository {
    val data: Flow<List<Place>>
    suspend fun insert(place: Place)
    suspend fun removeById(id: Long)
}