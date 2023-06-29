package com.example.capstoneproject9.presentation.edit_profile_info

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val repo: ProfileRepository
): ViewModel(){

    val user = repo.user
    var saveProfileReponse by mutableStateOf<Response<Boolean>>(Success(false))
    private set


    fun saveProfile(
        recipientName: String,
        contactNumber: String,
        houseNumber: String,
        city: String,
        country: String,
        zipCode: String
    ) = viewModelScope.launch {
        saveProfileReponse = Loading
        saveProfileReponse = repo.saveProfileInfoInFirestore(recipientName, contactNumber, houseNumber, city, country, zipCode)
    }
}