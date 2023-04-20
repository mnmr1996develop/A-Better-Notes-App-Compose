package com.michaelrichards.anothernoteapp.featureNote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.michaelrichards.anothernoteapp.ui.theme.*
import java.util.UUID

@Entity
data class Note(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val dateCreated: Long,
    val lastEdited: Long,
    val color: Int,
) {
    companion object {
        val noteColors = listOf(yellow, lightBlue, violet, red, orange, pink, purple, limeGreen)
    }
}
