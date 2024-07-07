package com.farhan.stickynotesinkotlin

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import com.farhan.stickynotesinkotlin.databinding.ActivityDetailsBinding
import com.google.gson.Gson

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private var noteItem: NoteItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteItem = Gson().fromJson(intent.getStringExtra("data"), NoteItem::class.java)

        supportActionBar?.apply {
            title = noteItem?.title
            setDisplayHomeAsUpEnabled(true)
        }

        binding.apply {
            title.text = noteItem?.title
            created.text = "Created at: ${noteItem?.createdAt}"
            updated.text = "Updated at: ${noteItem?.updatedAt}"
            details.text = noteItem?.description

            edit.setOnClickListener {
                startActivity(
                    Intent(this@DetailsActivity, AddEditNoteItemActivity::class.java).apply {
                        putExtra("data", Gson().toJson(noteItem))
                    }
                )
                finish()
            }
            delete.setOnClickListener {
                AlertDialog.Builder(this@DetailsActivity).apply {
                    setTitle("Sure to delete?")
                    setMessage("Are you sure you want to delete?")
                    setCancelable(false)
                    setPositiveButton("Yes, delete") { _, _ ->
                        noteItem?.let {
                            AppDatabase.getDatabase(this@DetailsActivity).noteItemDao().delete(it)
                        }
                        Toast.makeText(this@DetailsActivity, "Deleted", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    setNegativeButton(android.R.string.no) { dialog, _ ->
                        dialog.dismiss()
                    }
                    show()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
