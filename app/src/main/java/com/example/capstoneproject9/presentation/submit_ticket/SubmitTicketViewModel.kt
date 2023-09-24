package com.example.capstoneproject9.presentation.submit_ticket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.Loading
import com.example.capstoneproject9.domain.model.Response.Success
import com.example.capstoneproject9.domain.repository.TicketingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubmitTicketViewModel @Inject constructor(
    private val repo: TicketingRepository
): ViewModel() {

    var submitTicketResponse by mutableStateOf<Response<Boolean>>(Success(false))
        private set

    fun submitTicket(subject: String ,contantNumber:String, email: String, problem: String) = viewModelScope.launch {
        submitTicketResponse = Loading
        submitTicketResponse = repo.SubmitTicketInFirestore(subject, contantNumber, email, problem)
    }
}