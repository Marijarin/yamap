package ru.netology.travelmark.app.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.netology.travelmark.app.db.PlaceDb
import ru.netology.travelmark.app.dto.Place
import ru.netology.travelmark.app.repository.PlacesRepository
import ru.netology.travelmark.app.repository.PlacesRepositoryImpl

class MapViewModel (application: Application): AndroidViewModel(application) {
    private val repository: PlacesRepository = PlacesRepositoryImpl (PlaceDb.getInstance(application).placeDao())
val data: Flow<List<Place>>
    get() = repository.data
    init {
        loadPlaces()
    }

    fun loadPlaces()= viewModelScope.launch{
        try {
           repository.data.map{ it.asReversed() }

        } catch (e: Exception){
           Toast
               .makeText(getApplication(), "Something went wrong: $e", Toast.LENGTH_SHORT)
               .show()
        }
    }

    fun save(place: Place) = viewModelScope.launch{
        try {
            repository.insert(place)
        } catch (e: Exception){
            Toast
                .makeText(getApplication(), "Something went wrong: $e", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun removeById(id: Long)= viewModelScope.launch{
        try {
            repository.removeById(id)
        } catch (e: Exception){
            Toast
                .makeText(getApplication(), "Something went wrong: $e", Toast.LENGTH_SHORT)
                .show()
        }
    }
}