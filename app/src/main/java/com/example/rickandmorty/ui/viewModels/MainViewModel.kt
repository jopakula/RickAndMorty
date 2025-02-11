package com.example.rickandmorty.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.RequestState
import com.example.rickandmorty.domain.models.MyCharacter
import com.example.rickandmorty.domain.useCases.FetchCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val fetchCharactersUseCase: FetchCharactersUseCase
) : ViewModel() {

    private val charactersStateMutable = MutableStateFlow<RequestState<List<MyCharacter>>>(RequestState.Idle)
    val charactersState: StateFlow<RequestState<List<MyCharacter>>> = charactersStateMutable

    private val charactersList = mutableListOf<MyCharacter>()

    private var currentPage = 1

    private var isLastPage = false
    private var isLoading = false

    fun loadCharacters() {
        if (isLoading || isLastPage) return

        isLoading = true
        charactersStateMutable.value = RequestState.Loading

        viewModelScope.launch {
            val request = fetchCharactersUseCase.execute(page = currentPage)
            when (request) {
                is RequestState.Success -> {
                    if (request.data.isEmpty()) {
                        isLastPage = true
                    } else {
                        charactersList.addAll(request.data)
                        charactersStateMutable.value = RequestState.Success(charactersList.toList())
                        currentPage++
                    }
                }
                is RequestState.Error -> {
                    charactersStateMutable.value = RequestState.Error(request.message)
                }
                else -> {}
            }
            isLoading = false
        }
    }
}

