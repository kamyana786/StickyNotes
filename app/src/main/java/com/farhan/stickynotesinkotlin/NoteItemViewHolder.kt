package com.farhan.stickynotesinkotlin

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.farhan.stickynotesinkotlin.R

class NoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val details: TextView = itemView.findViewById(R.id.details)
    val date: TextView = itemView.findViewById(R.id.date)
    val post: LinearLayout = itemView.findViewById(R.id.post)
}
