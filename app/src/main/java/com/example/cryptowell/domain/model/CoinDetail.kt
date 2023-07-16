package com.example.cryptowell.domain.model

import com.example.cryptowell.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId:String,
    val coinName:String,
    val description:String,
    val symbol:String,
    val rank:Int,
    val isActive:Boolean,
    val tags:List<String>,
    val team:List<TeamMember>
)
