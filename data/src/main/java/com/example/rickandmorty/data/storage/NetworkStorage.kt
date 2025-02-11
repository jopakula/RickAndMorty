package com.example.rickandmorty.data.storage

import com.example.rickandmorty.data.storage.network.models.CharacterResponseData

interface NetworkStorage {
    suspend fun fetchCharacters(page: Int): CharacterResponseData
}