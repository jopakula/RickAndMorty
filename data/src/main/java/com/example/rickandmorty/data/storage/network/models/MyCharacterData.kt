package com.example.rickandmorty.data.storage.network.models

data class MyCharacterData(
    val id: Int,
    val name: String,
    val status: String,
    val gender: String,
    val image: String,
    val origin: OriginData
)