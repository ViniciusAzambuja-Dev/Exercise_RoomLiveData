package com.example.roomlivedata

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.roomlivedata.data.AppDatabase
import com.example.roomlivedata.data.PersonagemDAO
import com.example.roomlivedata.viewModel.PersonagemViewModel
import kotlinx.coroutines.launch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    private lateinit var personagemDAO: PersonagemDAO
    private lateinit var personagemViewModel : PersonagemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getDatabase(this)
        personagemDAO = db.personagemDAO()
        personagemViewModel = PersonagemViewModel(personagemDAO)

        personagemDAO.selectAllLiveData().observe(this) { personagens ->
            personagens?.let {
                for (personagem in it) {
                    println("Nome: ${personagem.nome}, Nível: ${personagem.nivel}")
                }
            }
        }

        setContent {
            criarPersonagemScreen(::criarPersonagem, ::listarPersonagem, ::atualizarPersonagem, ::deletarPersonagem)
        }
    }

    fun criarPersonagem(){
        lifecycleScope.launch {
            personagemViewModel.salvarPersonagem()
        }
    }

    fun listarPersonagem(){
        lifecycleScope.launch {
            personagemViewModel.getPersonagens()
        }
    }

    fun atualizarPersonagem(){
        lifecycleScope.launch{
            personagemViewModel.atualizarPersonagem()
        }
    }

    fun deletarPersonagem(){
        lifecycleScope.launch {
            personagemViewModel.deletarPersonagem()
        }
    }

}

@Composable
fun criarPersonagemScreen(
    criarPersonagem: ()-> Unit,
    listarPersonagem: ()->Unit,
    atualizarPersonagem: ()->Unit,
    deletarPersonagem: ()->Unit,
){

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Button(onClick = criarPersonagem) {
            Text("Criar Personagem")
        }

        Button(onClick = atualizarPersonagem) {
            Text("Atualizar Personagem")
        }

        Button(onClick = deletarPersonagem) {
            Text("Deletar Personagem")
        }

        Button(onClick = listarPersonagem) {
            Text("Listar Personagem")
        }
    }

}