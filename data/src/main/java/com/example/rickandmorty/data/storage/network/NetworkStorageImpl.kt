package com.example.rickandmorty.data.storage.network

import com.example.rickandmorty.data.storage.NetworkStorage
import com.example.rickandmorty.data.storage.network.models.CharacterResponseData

class NetworkStorageImpl(
    private val api: Api
): NetworkStorage {
    override suspend fun fetchCharacters(page: Int): CharacterResponseData {
        return api.getCharacters(page = page)
    }
}