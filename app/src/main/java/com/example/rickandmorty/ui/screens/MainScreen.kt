package com.example.rickandmorty.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.domain.RequestState
import com.example.rickandmorty.domain.models.MyCharacter
import com.example.rickandmorty.ui.items.CharacterItem
import com.example.rickandmorty.ui.items.ShimmerListItem
import com.example.rickandmorty.ui.viewModels.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val charactersState by viewModel.charactersState.collectAsState()
    val listState = rememberLazyListState()

    var progress by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            for (i in 0..100) {
                progress = i / 100f
                delay(20)
            }
            delay(150)
            viewModel.loadCharacters()
        }
    }

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
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LinearProgressIndicator(
                        progress = { progress },
                        trackColor = Color.White.copy(alpha = 0.12F),
                        modifier = Modifier
                            .width(206.dp)
                            .height(10.dp)
                            .clip(shape = RoundedCornerShape(4.dp)),
                        color = Color.Green,
                        gapSize = 0.dp,
                        drawStopIndicator = {}
                    )
                    Text(
                        text = "Character loading ${(progress * 100).toInt()}%",
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }
            }
            is RequestState.Loading -> {
                Column(
                    modifier = Modifier
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Characters",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    repeat(10) {
                        ShimmerListItem(isLoading = true) {
                        }
                    }
                }
            }
            is RequestState.Success -> {
                val characters = (charactersState as RequestState.Success<List<MyCharacter>>).data
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Characters",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        state = listState,
                    ) {
                        items(characters) { character ->
                            CharacterItem(character)
                        }
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
