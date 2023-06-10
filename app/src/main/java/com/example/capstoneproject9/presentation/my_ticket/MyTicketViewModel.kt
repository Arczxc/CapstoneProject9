package com.example.capstoneproject9.presentation.my_ticket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject9.domain.model.Response.Loading
import com.example.capstoneproject9.domain.repository.MyTicketResponse
import com.example.capstoneproject9.domain.repository.TicketingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyTicketViewModel @Inject constructor(
    private val repo: TicketingRepository
): ViewModel() {

    var myTicketResponse by mutableStateOf<MyTicketResponse>(Loading)
    private set

    init {
        getMyTicket()
    }

    private fun getMyTicket() = viewModelScope.launch {
        myTicketResponse = repo.GetTicketInFirestore()
    }
}