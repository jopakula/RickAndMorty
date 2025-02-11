package com.example.rickandmorty.data.storage.network

import com.example.rickandmorty.data.storage.network.models.CharacterResponseData
import retrofit2.http.GET

interface Api {
    @GET("character")
    suspend fun getCharacters(): CharacterResponseData
}
