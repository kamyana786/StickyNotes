package com.farhan.stickynotesinkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farhan.stickynotesinkotlin.databinding.ItemNoteBinding
import com.google.gson.Gson

class NoteItemAdapter(private val context: Context) :
    RecyclerView.Adapter<NoteItemAdapter.NoteItemViewHolder>() {

    private var data: List<NoteItem> = emptyList() // Initial empty list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(context), parent, false)
        return NoteItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val noteItem = data[position]
        holder.bind(noteItem)
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<NoteItem>) {
        data = newData
        notifyDataSetChanged()
    }

    inner class NoteItemViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(noteItem: NoteItem) {
            binding.apply {
                title.text = noteItem.title
                details.text = noteItem.description
                date.text = noteItem.createdAt
                root.setOnClickListener {
                    val intent = Intent(context, DetailsActivity::class.java).apply {
                        putExtra("data", Gson().toJson(noteItem))
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
}
