package ru.netology.travelmark.app.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.travelmark.app.dto.Place

@Entity
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
)
{
    fun toDto() = Place(
        id,
        name,
        latitude,
        longitude,
        description
    )

    companion object {
        fun fromDto(dto: Place) =
            PlaceEntity(
                dto.id,
                dto.name,
                dto.latitude,
                dto.longitude,
                dto.description,
            )
    }
}

fun List<PlaceEntity>.toDto(): List<Place> = map(PlaceEntity::toDto)
fun List<Place>.toEntity(): List<PlaceEntity> = map(PlaceEntity::fromDto)