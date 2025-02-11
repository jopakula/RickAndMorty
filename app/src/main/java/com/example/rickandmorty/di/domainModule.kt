package com.example.rickandmorty.di

import com.example.rickandmorty.domain.useCases.FetchCharactersUseCase
import org.koin.dsl.module


val domainModule = module {

    factory<FetchCharactersUseCase> {
        FetchCharactersUseCase(repository = get())
    }

}
