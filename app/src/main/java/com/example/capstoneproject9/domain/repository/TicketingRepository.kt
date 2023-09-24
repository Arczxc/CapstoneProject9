package com.example.capstoneproject9.domain.repository

import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Ticket
import com.example.capstoneproject9.domain.model.User

typealias Tickets = List<Ticket>
typealias MyTicketResponse = Response<Tickets>
typealias SubmitTicketResponse = Response<Boolean>
typealias DeleteTicketResponse = Response<Boolean>

interface TicketingRepository{

    val user: User

    suspend fun SubmitTicketInFirestore(subject: String ,contantNumber:String, email: String, problem: String): SubmitTicketResponse

    suspend fun GetTicketInFirestore() : MyTicketResponse

    suspend fun DeleteTicket( ticketId: String ) : DeleteTicketResponse

}