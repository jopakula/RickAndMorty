package com.example.rickandmorty.domain.models

data class MyCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val gender: String,
    val image: String,
    val origin: Origin
)