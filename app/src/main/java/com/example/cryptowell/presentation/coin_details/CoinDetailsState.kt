package com.example.cryptowell.presentation.coin_details

import com.example.cryptowell.common.Resource
import com.example.cryptowell.domain.model.Coin
import com.example.cryptowell.domain.model.CoinDetail

data class CoinDetailsState(
    val isLoading: Boolean=false,
    val coin:CoinDetail?=null,
    val error:String=""
)
