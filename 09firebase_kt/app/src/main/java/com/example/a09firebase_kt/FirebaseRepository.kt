package com.example.a09firebase_kt

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

//List<out T> in Kotlin is equivalent to List<? extends T> in Java.
sealed class UiState<out T> {
    class Loading<out T> : UiState<T>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Failed<out T>(val message: String) : UiState<T>()
}
class FirebaseRepository {
    private val fireStoreInstance = Firebase.firestore.collection("todo_collection")
    suspend fun addTodoToFirestore(todo: Todo) = flow {
        try {
            emit(UiState.Loading())
            fireStoreInstance.add(todo).await()
            emit(UiState.Success(todo))
        } catch (e: Exception) {
            emit(UiState.Failed(e.message ?: e.toString()))
        }
    }

    suspend fun getTodosFromFirestore(): Flow<UiState<List<Todo>>> = callbackFlow {
        trySend(UiState.Loading()).isSuccess

        val snapshotListener = fireStoreInstance.addSnapshotListener { snapshot, err ->
            if (snapshot != null) {
                val todos = snapshot.toObjects<Todo>()
                trySend(UiState.Success(todos)).isSuccess
            } else {
                trySend(UiState.Failed(err?.message ?: err.toString())).isFailure
            }
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

}