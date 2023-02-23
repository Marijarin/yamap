package ru.netology.travelmark.app.repository

import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.netology.travelmark.app.dao.PlaceDao
import ru.netology.travelmark.app.dto.Place
import ru.netology.travelmark.app.entity.PlaceEntity
import ru.netology.travelmark.app.entity.toDto

class PlacesRepositoryImpl (private val dao: PlaceDao): PlacesRepository {
    override val data: Flow<List<Place>>
        get() = dao.getAll().map(List<PlaceEntity>::toDto).flowOn(Dispatchers.Default)


    override suspend fun insert(place: Place) {
        try {
            dao.insert(PlaceEntity.fromDto(place))
        } catch (e: Exception){
            println("*****************$e*************")
        }
    }

    override suspend fun removeById(id: Long) {
        try {
            dao.removeById(id)
        } catch (e: Exception){
            println("*****************$e*************")
        }
    }


}