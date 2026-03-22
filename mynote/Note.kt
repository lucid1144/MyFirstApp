package com.example.mynote

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val date: String,
    val description: String
)
