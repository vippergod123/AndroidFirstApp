package com.example.androidfirstapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.task_view_holder.view.*

class ListItemRecyclerViewAdapter (var list:TaskList): RecyclerView.Adapter<ListItemViewHolder>() {
    //region Override Func
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_view_holder,parent,false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.taskTextView.text = list.tasks[position]
    }
    //endregion
}