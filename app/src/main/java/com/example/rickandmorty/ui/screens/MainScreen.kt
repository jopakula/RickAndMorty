package com.example.rickandmorty.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.domain.RequestState
import com.example.rickandmorty.domain.models.MyCharacter
import com.example.rickandmorty.ui.items.CharacterItem
import com.example.rickandmorty.ui.items.ShimmerListItem
import com.example.rickandmorty.ui.viewModels.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val charactersState by viewModel.charactersState.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisibleItemIndex != null && lastVisibleItemIndex >= totalItems - 3) {
                    viewModel.loadCharacters()
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (charactersState) {
            is RequestState.Idle -> {
                Button(
                    onClick = { viewModel.loadCharacters() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Получить персонажей")
                }
            }
            is RequestState.Loading -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    repeat(10) {
                        ShimmerListItem(isLoading = true) {
                        }
                    }
                }
            }
            is RequestState.Success -> {
                val characters = (charactersState as RequestState.Success<List<MyCharacter>>).data
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    state = listState,
                    ) {
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
