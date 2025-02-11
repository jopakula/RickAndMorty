package com.example.rickandmorty.di

import com.example.rickandmorty.ui.viewModels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel {
        MainViewModel(
           fetchCharactersUseCase = get()
        )
    }

}