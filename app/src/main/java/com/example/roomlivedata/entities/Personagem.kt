package com.example.roomlivedata.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personagem")
data class Personagem(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val nome : String,
    val nivel : Int
)