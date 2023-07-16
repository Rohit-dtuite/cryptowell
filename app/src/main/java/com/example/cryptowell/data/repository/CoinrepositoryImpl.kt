package com.example.cryptowell.data.repository

import com.example.cryptowell.data.remote.ApiService
import com.example.cryptowell.data.remote.dto.CoinDto
import com.example.cryptowell.data.remote.dto.CoindetailsDto
import com.example.cryptowell.domain.repository.CoinRepository
import javax.inject.Inject

class CoinrepositoryImpl @Inject constructor(
    private  val apiService: ApiService):CoinRepository{
    override suspend fun getCoins(): List<CoinDto> {
        return apiService.getCoins()
    }

    override suspend fun getCoinById(coiId: String): CoindetailsDto {
       return  apiService.getCoinbyId(coiId)
    }



}