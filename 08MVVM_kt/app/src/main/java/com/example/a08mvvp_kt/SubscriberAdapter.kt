package com.example.a08mvvp_kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubscriberAdapter(
    private val subscriberList: List<Subscriber>,
    private val clickListenerCallback: (Subscriber) -> Unit
) :
    RecyclerView.Adapter<SubscriberAdapter.SubscriberViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubscriberViewHolder {
        val inflatedView =
//          LayoutInflater.from(context)
            LayoutInflater.from(parent.context).inflate(R.layout.subscriber_layout, parent, false)
        return SubscriberViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: SubscriberViewHolder, position: Int) {
        holder.bind(subscriber = subscriberList[position])
    }

    override fun getItemCount(): Int = subscriberList.size

    inner class SubscriberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView.findViewById<TextView>(R.id.tvName)
        var tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
        fun bind(subscriber: Subscriber) {
            tvName.text = subscriber.name
            tvEmail.text = subscriber.email
            itemView.setOnClickListener {
                clickListenerCallback(subscriber)
            }
        }
    }
}