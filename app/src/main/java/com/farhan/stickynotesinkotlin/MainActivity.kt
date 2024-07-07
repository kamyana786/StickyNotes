package com.farhan.stickynotesinkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteItemAdapter
    private lateinit var notesLiveData: LiveData<List<NoteItem>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NoteItemAdapter(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        findViewById<View>(R.id.addFab).setOnClickListener {
            startActivity(Intent(this, AddEditNoteItemActivity::class.java))
        }

        // Observe data changes
        notesLiveData = AppDatabase.getDatabase(this).noteItemDao().getAll()
        notesLiveData.observe(this, Observer { notes ->
            notes?.let {
                adapter.updateData(it)
            }
        })
    }
}
