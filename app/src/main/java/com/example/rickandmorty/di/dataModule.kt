package com.example.rickandmorty.di

import com.example.rickandmorty.data.RepositoryImpl
import com.example.rickandmorty.data.storage.NetworkStorage
import com.example.rickandmorty.data.storage.network.Api
import com.example.rickandmorty.data.storage.network.ApiClient
import com.example.rickandmorty.data.storage.network.NetworkStorageImpl
import com.example.rickandmorty.domain.Repository
import org.koin.dsl.module

val dataModule = module {

    single<Api> { ApiClient.api }
    single<NetworkStorage> { NetworkStorageImpl(api = get()) }
    single<Repository> { RepositoryImpl(networkStorage = get())  }

}