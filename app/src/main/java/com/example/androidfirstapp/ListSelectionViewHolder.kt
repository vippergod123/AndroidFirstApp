package com.example.androidfirstapp

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ListSelectionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    val listPosition = itemView?.findViewById<TextView>(R.id.itemNumber) as TextView
    val listName = itemView?.findViewById<TextView>(R.id.itemString) as TextView
}