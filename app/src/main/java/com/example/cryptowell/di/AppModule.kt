package com.example.cryptowell.di

import com.example.cryptowell.common.Constants
import com.example.cryptowell.data.remote.ApiService
import com.example.cryptowell.data.repository.CoinrepositoryImpl
import com.example.cryptowell.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesApiService():ApiService {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun providesCoinRepository(apiService: ApiService):CoinRepository{
        return CoinrepositoryImpl(apiService)
    }
}