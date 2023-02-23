package ru.netology.travelmark.app.dto

data class Place(
    val id: Long = 0L,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
)
