package com.example.rickandmorty.domain

import com.example.rickandmorty.domain.models.CharacterResponse

interface Repository {
    suspend fun fetchCharacters(page: Int): CharacterResponse
}
