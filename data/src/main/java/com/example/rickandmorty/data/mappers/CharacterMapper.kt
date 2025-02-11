package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.storage.network.models.CharacterResponseData
import com.example.rickandmorty.data.storage.network.models.MyCharacterData
import com.example.rickandmorty.data.storage.network.models.OriginData
import com.example.rickandmorty.domain.models.CharacterResponse
import com.example.rickandmorty.domain.models.MyCharacter
import com.example.rickandmorty.domain.models.Origin

class CharacterMapper {
    companion object{

        fun mapCharacterResponseDataToDomain(responseData: CharacterResponseData): CharacterResponse {
            return CharacterResponse(
                results = responseData.results.map { mapMyCharacterDataToDomain(characterData = it) }
            )
        }

        fun mapMyCharacterDataToDomain(characterData: MyCharacterData): MyCharacter{
            return MyCharacter(
                id = characterData.id,
                name = characterData.name,
                status = characterData.status,
                species = characterData.species,
                gender = characterData.gender,
                image = characterData.image,
                origin = mapOriginDataToDomain(origin = characterData.origin)
            )
        }

        fun mapOriginDataToDomain(origin: OriginData): Origin{
            return Origin(name = origin.name)
        }
    }
}