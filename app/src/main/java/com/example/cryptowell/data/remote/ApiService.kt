package com.example.cryptowell.data.remote

import com.example.cryptowell.data.remote.dto.CoinDto
import com.example.cryptowell.data.remote.dto.CoindetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/v1/coins")
   suspend fun getCoins():List<CoinDto>

   @GET("/v1/coins/{coinId}")
   suspend fun getCoinbyId(@Path("coinId")coinId:String):CoindetailsDto
}