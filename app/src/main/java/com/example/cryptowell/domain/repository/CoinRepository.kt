package com.example.cryptowell.domain.repository

import com.example.cryptowell.data.remote.dto.CoinDto
import com.example.cryptowell.data.remote.dto.CoindetailsDto

interface CoinRepository {
    suspend fun getCoins():List<CoinDto>

    suspend fun getCoinById(coiId:String):CoindetailsDto
}