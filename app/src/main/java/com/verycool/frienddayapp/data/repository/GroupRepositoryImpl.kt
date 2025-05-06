package com.verycool.frienddayapp.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.verycool.frienddayapp.data.model.Group
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GroupRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val dispatcher: CoroutineDispatcher
) : GroupRepository {

    override fun getGroup(groupId: Int): Flow<Group?> = callbackFlow {
        val docRef = firestore.collection("groups").document(groupId.toString())
        val listener = docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            trySend(snapshot?.toObject(Group::class.java))
        }
        awaitClose { listener.remove() }
    }.flowOn(dispatcher)

    override suspend fun getGroupOnce(groupId: Int): Group? = withContext(dispatcher) {
        try {
            val snapshot = firestore.collection("groups")
                .document(groupId.toString())
                .get()
                .await()
            snapshot.toObject(Group::class.java)
        } catch (e: Exception) {
            Log.e("GroupRepo", "Error fetching group $groupId", e)
            null
        }
    }

    override fun getAllGroups(): Flow<List<Group>> = callbackFlow {
        val colRef = firestore.collection("groups")
        val listener = colRef.addSnapshotListener { snapshots, _ ->
            val groups = snapshots?.documents?.mapNotNull { it.toObject(Group::class.java) } ?: emptyList()
            trySend(groups)
        }
        awaitClose { listener.remove() }
    }
}



