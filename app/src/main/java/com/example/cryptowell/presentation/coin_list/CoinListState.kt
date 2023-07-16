package com.example.cryptowell.presentation.coin_list

import com.example.cryptowell.common.Resource
import com.example.cryptowell.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean=false,
    val coins:List<Coin> = emptyList(),
    val error:String=""
)
