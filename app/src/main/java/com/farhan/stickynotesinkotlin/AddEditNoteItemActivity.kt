package com.farhan.stickynotesinkotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.farhan.stickynotesinkotlin.databinding.ActivityAddEditNoteItemBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class AddEditNoteItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteItemBinding
    private var noteItem: NoteItem = NoteItem()
    private var isForEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Edit notes"
        }

        if (intent.hasExtra("data")) {
            noteItem = Gson().fromJson(intent.getStringExtra("data"), NoteItem::class.java)
            isForEdit = true

            binding.title.setText(noteItem.title)
            binding.details.setText(noteItem.description)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add_notes, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                saveNote()
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {
        if (binding.title.text.toString().isEmpty() && binding.details.text.toString().isEmpty()) {
            Toast.makeText(
                this,
                "Both title and details could not be empty",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        noteItem.title = binding.title.text.toString()
        noteItem.description = binding.details.text.toString()

        val currentDateTime = SimpleDateFormat("dd-MM-yyyy HH:mm a").format(Date())
        if (!isForEdit) noteItem.createdAt = currentDateTime
        noteItem.updatedAt = currentDateTime

        // Run database operation in a background thread using coroutines
        lifecycleScope.launch(Dispatchers.IO) {
            AppDatabase.getDatabase(this@AddEditNoteItemActivity).noteItemDao().insertOrReplace(noteItem)
            launch(Dispatchers.Main) {
                Toast.makeText(this@AddEditNoteItemActivity, "Note saved successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
