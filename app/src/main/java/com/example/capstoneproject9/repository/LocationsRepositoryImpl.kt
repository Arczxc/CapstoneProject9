package com.example.capstoneproject9.repository

import com.example.capstoneproject9.core.AppConstants
import com.example.capstoneproject9.core.FirebaseConstants
import com.example.capstoneproject9.core.FirebaseConstants.LOCATION_INFO
import com.example.capstoneproject9.core.FirebaseConstants.USERS
import com.example.capstoneproject9.domain.model.Location
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.model.User
import com.example.capstoneproject9.domain.repository.AddLocationResponse
import com.example.capstoneproject9.domain.repository.DeleteLocationResponse
import com.example.capstoneproject9.domain.repository.LocationsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationsRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    auth: FirebaseAuth
): LocationsRepository {

    override val user = User(
        uid = auth.currentUser?.uid ?: AppConstants.NO_VALUE,
        photoUrl = auth.currentUser?.photoUrl.toString(),
        displayName = auth.currentUser?.displayName ?: AppConstants.NO_VALUE,
        email = auth.currentUser?.email ?: AppConstants.NO_VALUE,
    )

    val uid = auth.currentUser?.uid ?: AppConstants.NO_VALUE
    private val usersRef = firebaseFirestore.collection(USERS)
    private val locationsRef = usersRef.document(uid).collection(LOCATION_INFO)


    override fun getLocationsFromFirestore() = callbackFlow {
        val snapshotListener = locationsRef.addSnapshotListener { snapshot, e ->
            val locationsResponse = if (snapshot != null) {
                val locations = snapshot.toObjects(Location::class.java)
                Response.Success(locations)
            } else {
                Failure(e)
            }
            trySend(locationsResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addLocationToFirestore(lat: Double, lng: Double): AddLocationResponse {
        return try {
            val id = locationsRef.document().id
            val location = Location(
                id = id,
                lat = lat,
                lng = lng
            )
            locationsRef.document(id).set(location).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun deleteLocationFromFirestore(locationId: String): DeleteLocationResponse {
        return try {
            locationsRef.document(locationId).delete().await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}