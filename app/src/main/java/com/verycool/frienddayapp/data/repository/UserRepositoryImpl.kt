package com.verycool.frienddayapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.verycool.frienddayapp.R
import com.verycool.frienddayapp.data.model.Group
import com.verycool.frienddayapp.data.model.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firestore : FirebaseFirestore
): UserRepository {

    override suspend fun updateUserSelectedDates(uid: String, dates: List<String>) {
        val userDocRef = firestore.collection("users").document(uid)
        userDocRef.update("selectedDateTimes", dates)
    }

    override suspend fun getUserByUid(uid: String): Flow<User?> = callbackFlow {
        val docRef = firestore.collection("users").document(uid)
        val listener = docRef.addSnapshotListener { snapshot, _ ->
            if (snapshot != null && snapshot.exists()) {
                val user = snapshot.toObject(User::class.java)
                trySend(user)
            } else {
                trySend(null)
            }
        }
        awaitClose { listener.remove() }
    }

    override fun getUser(userId: Int): Flow<User?> = callbackFlow {
        val docRef = firestore.collection("users").document(userId.toString())
        val listener = docRef.addSnapshotListener { snapshot, _ ->
            if(snapshot != null && snapshot.exists()){
                trySend(snapshot.toObject(User::class.java))
            } else {
                trySend(null)
            }
        }
        awaitClose { listener.remove() }
    }

    override fun getAllUsers(): Flow<List<User>> = callbackFlow {
        val colRef = firestore.collection("users")
        val listener = colRef.addSnapshotListener { snapshots, _ ->
            val users = snapshots?.documents?.mapNotNull { it.toObject(User::class.java) } ?: emptyList()
            trySend(users)
        }
        awaitClose { listener.remove() }
    }
}
