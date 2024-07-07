package com.farhan.stickynotesinkotlin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteItem(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var description: String? = null
) {
    constructor(title: String?, createdAt: String?, updatedAt: String?, description: String?) : this() {
        this.title = title
        this.createdAt = createdAt
        this.updatedAt = updatedAt
        this.description = description
    }
}
