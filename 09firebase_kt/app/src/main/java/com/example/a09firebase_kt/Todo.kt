package com.example.a09firebase_kt

import com.google.firebase.firestore.DocumentId

data class Todo(
    var title: String? = "",
    @DocumentId var todoId: String? = "",
)