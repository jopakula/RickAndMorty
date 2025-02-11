package com.example.rickandmorty.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.domain.RequestState
import com.example.rickandmorty.domain.models.MyCharacter
import com.example.rickandmorty.ui.items.CharacterItem
import com.example.rickandmorty.ui.viewModels.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val charactersState by viewModel.charactersState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Button(
            onClick = { viewModel.loadCharacters() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Получить персонажей")
        }

        when (charactersState) {
            is RequestState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is RequestState.Success -> {
                val characters = (charactersState as RequestState.Success<List<MyCharacter>>).data
                LazyColumn {
                    items(characters) { character ->
                        CharacterItem(character)
                    }
                }
            }

            is RequestState.Error -> {
                Text(
                    text = "Ошибка: ${(charactersState as RequestState.Error).message}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            is RequestState.Empty -> {
                Text(
                    text = "Нет данных",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {}
        }
    }
}