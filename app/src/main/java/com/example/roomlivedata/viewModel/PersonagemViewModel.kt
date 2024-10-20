package com.example.roomlivedata.viewModel

import androidx.lifecycle.ViewModel
import com.example.roomlivedata.data.PersonagemDAO
import com.example.roomlivedata.entities.Personagem

class PersonagemViewModel(private var personagemDAO: PersonagemDAO) : ViewModel() {

    suspend fun salvarPersonagem(){
        val personagem = Personagem(nome = "Legolas", nivel = 10)
        personagemDAO.insert(personagem)
    }

    suspend fun getPersonagens(){
        val listaPersonagem = personagemDAO.selectAll()
        if (listaPersonagem.isNotEmpty()) {
            for (personagem in listaPersonagem) {
                println("NOME: ${personagem.nome}, NIVEL: ${personagem.nivel}")
            }
        }
    }

    suspend fun atualizarPersonagem(){
        val personagemExistente = personagemDAO.selectAll().first()
        val personagemAtualizado = personagemExistente.copy(nivel = 20)
        personagemDAO.update(personagemAtualizado)
    }

    suspend fun deletarPersonagem(){
        val personagemParaDeletar = personagemDAO.selectAll().first()
        personagemDAO.delete(personagemParaDeletar)
    }
}