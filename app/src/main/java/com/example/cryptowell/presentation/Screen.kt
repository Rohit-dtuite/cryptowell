package com.example.cryptowell.presentation

sealed class Screen(val route:String){
    object CoinListScreen:Screen("Coin_list_screen")
    object CoinDetailScreen:Screen("Coin_details_screen")
}
