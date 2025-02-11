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
): ViewModel() {

    private val charactersStateMutable = MutableStateFlow<RequestState<List<MyCharacter>>>(RequestState.Idle)
    val charactersState: StateFlow<RequestState<List<MyCharacter>>> = charactersStateMutable

    fun loadCharacters() {
        charactersStateMutable.value = RequestState.Loading
        viewModelScope.launch {
            charactersStateMutable.value = fetchCharactersUseCase.execute()
        }
    }
}
