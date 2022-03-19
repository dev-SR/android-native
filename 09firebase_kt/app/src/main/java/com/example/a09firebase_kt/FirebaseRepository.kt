package com.example.a09firebase_kt

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore


class FirebaseRepository {
    private val fireStoreInstance = FirebaseFirestore.getInstance().collection("todo_collection")


    fun read(cb: (List<Todo>) -> Unit) {
        fireStoreInstance.addSnapshotListener { querySnapshot, _ ->
            cb(querySnapshot?.toObjects(Todo::class.java)!!)

        }
//        fireStoreInstance.get().addOnCompleteListener { task ->
//            task.result?.let {
//                cb(task.result!!.toObjects(Todo::class.java))
//            };
////            if (task.isSuccessful) {
////                for (document in task.result!!) {
////                    Log.d("FireStore", document.id + " => " + document.data)
////                }
////            } else {
////                Log.w("FireStore", "Error getting documents.", task.exception)
////            }
//        }
    }

    fun add(todo: Todo, cb: (Todo) -> Unit) {
        fireStoreInstance.add(todo)
    }


}