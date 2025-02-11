package com.example.rickandmorty.domain.useCases

import com.example.rickandmorty.domain.Repository
import com.example.rickandmorty.domain.RequestState
import com.example.rickandmorty.domain.models.MyCharacter

class FetchCharactersUseCase(private val repository: Repository) {
    suspend fun execute(page: Int): RequestState<List<MyCharacter>> {
        return try {
            val response = repository.fetchCharacters(page = page)
            if (response.results.isEmpty()) {
                RequestState.Empty
            } else {
                RequestState.Success(response.results)
            }
        } catch (e: Exception) {
            RequestState.Error(e.localizedMessage ?: "Unknown error")
        }
    }
}
