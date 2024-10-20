package com.example.roomlivedata.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomlivedata.entities.Personagem

@Dao
interface PersonagemDAO {
    @Insert
    suspend fun insert(personagem : Personagem)

    @Query("SELECT * FROM personagem")
    suspend fun selectAll() : List<Personagem>

    @Query("SELECT * FROM personagem")
    fun selectAllLiveData() : LiveData<List<Personagem>>

    @Update
    suspend fun update(personagem: Personagem)

    @Delete
    suspend fun delete(personagem: Personagem)
}