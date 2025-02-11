package com.example.rickandmorty.data

import com.example.rickandmorty.data.mappers.CharacterMapper
import com.example.rickandmorty.data.storage.NetworkStorage
import com.example.rickandmorty.domain.Repository
import com.example.rickandmorty.domain.models.CharacterResponse

class RepositoryImpl(
    private val networkStorage: NetworkStorage
) : Repository {
    override suspend fun fetchCharacters(page: Int): CharacterResponse {
        val responseData = networkStorage.fetchCharacters(page = page)
        return CharacterMapper.mapCharacterResponseDataToDomain(responseData)
    }
}
