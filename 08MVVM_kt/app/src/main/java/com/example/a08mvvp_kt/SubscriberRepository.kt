package com.example.a08mvvp_kt

import android.content.Context
import kotlinx.coroutines.flow.Flow


class SubscriberRepository(context: Context) {
    private val dao: SubscriberDAO = SubscriberDatabase.getInstance(context).subscriberDAO

//    val subscribers = dao.getAllSubscribers()
//    fun getAllSubscriber(): LiveData<List<Subscriber>> {
//        return dao.getAllSubscribers()
//    }
    fun getAllSubscriber(): Flow<List<Subscriber>> {
        return dao.getAllSubscribers()
    }



    suspend fun insert(subscriber: Subscriber): Long {
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber): Int {
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber): Int {
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}
