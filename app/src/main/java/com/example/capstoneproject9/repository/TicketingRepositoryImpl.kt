package com.example.capstoneproject9.repository

import com.example.capstoneproject9.core.AppConstants
import com.example.capstoneproject9.core.FirebaseConstants
import com.example.capstoneproject9.core.FirebaseConstants.DATE_OF_SUBMISSION
import com.example.capstoneproject9.core.FirebaseConstants.ID
import com.example.capstoneproject9.core.FirebaseConstants.PROBLEM
import com.example.capstoneproject9.core.FirebaseConstants.PRODUCTS_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.SUBJECT
import com.example.capstoneproject9.core.FirebaseConstants.SUBMITTED_TICKET
import com.example.capstoneproject9.core.FirebaseConstants.USERS
import com.example.capstoneproject9.domain.model.Faq
import com.example.capstoneproject9.domain.model.*
import com.example.capstoneproject9.domain.model.Response.Failure
import com.example.capstoneproject9.domain.model.Response.Success
import com.example.capstoneproject9.domain.model.User
import com.example.capstoneproject9.domain.repository.MyTicketResponse
import com.example.capstoneproject9.domain.repository.SubmitTicketResponse
import com.example.capstoneproject9.domain.repository.TicketingRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.DESCENDING
import kotlinx.coroutines.tasks.await

class TicketingRepositoryImpl(
    private val firebaseFirestore: FirebaseFirestore,
    auth: FirebaseAuth
): TicketingRepository {

    override val user = User(
        uid = auth.currentUser?.uid ?: AppConstants.NO_VALUE,
        photoUrl = auth.currentUser?.photoUrl.toString(),
        displayName = auth.currentUser?.displayName ?: AppConstants.NO_VALUE,
        email = auth.currentUser?.email ?: AppConstants.NO_VALUE
    )

    val uid = auth.currentUser?.uid ?: AppConstants.NO_VALUE
    private val usersRef = firebaseFirestore.collection(USERS)
    private val submitTicketRef = usersRef.document(uid).collection(SUBMITTED_TICKET)
    private val submittedTicketRef = firebaseFirestore.collection(USERS).document(user.uid).collection(SUBMITTED_TICKET)

    override suspend fun SubmitTicketInFirestore(subject: String , problem: String): SubmitTicketResponse {
        return try {
            val orderId = submitTicketRef.document().id
            SubmittedTicket(orderId, subject, problem)
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun GetTicketInFirestore(): MyTicketResponse {
        return try {
            val ticket = submittedTicketRef.orderBy(DATE_OF_SUBMISSION, DESCENDING)
            val myTicket = ticket.get().await().toObjects(Ticket::class.java)
            Success(myTicket)
        } catch (e: Exception){
            Failure(e)
        }
    }

    private suspend fun SubmittedTicket(
        orderId: String,
        Subject: String,
        Problem: String
    ) = submitTicketRef.document(orderId).set(mapOf(
        ID to orderId,
        DATE_OF_SUBMISSION to serverTimestamp(),
        SUBJECT to Subject,
        PROBLEM to Problem
    )).await()
}