package com.example.capstoneproject9.domain.repository

import com.example.capstoneproject9.domain.model.Location
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.User
import kotlinx.coroutines.flow.Flow

typealias Locations = List<Location>
typealias LocationsResponse = Response<Locations>
typealias AddLocationResponse = Response<Boolean>
typealias DeleteLocationResponse = Response<Boolean>

interface LocationsRepository{

    val user: User

    fun getLocationsFromFirestore(): Flow<LocationsResponse>

    suspend fun addLocationToFirestore(lat: Double, lng: Double): AddLocationResponse

    suspend fun deleteLocationFromFirestore(locationId: String): DeleteLocationResponse

}