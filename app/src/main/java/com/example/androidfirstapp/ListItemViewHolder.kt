package com.example.androidfirstapp

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val taskTextView = itemView.findViewById<TextView>(R.id.task_textView) as TextView
}