package com.example.rickandmorty.data.storage.network

import com.example.rickandmorty.data.storage.network.models.CharacterResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponseData
}
