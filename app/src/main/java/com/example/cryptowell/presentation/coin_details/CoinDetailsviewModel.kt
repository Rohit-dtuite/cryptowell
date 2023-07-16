package com.example.cryptowell.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptowell.common.Constants
import com.example.cryptowell.common.Resource
import com.example.cryptowell.domain.use_case.get_SpecificCoin.GetSpecificCoin_usecase
import com.example.cryptowell.domain.use_case.get_coins.GetCoins_usecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsviewModel @Inject constructor(
    private val getspecificcoinUsecase: GetSpecificCoin_usecase,
    private val savedStateHandle: SavedStateHandle
):ViewModel(){
    private val _state= mutableStateOf(CoinDetailsState())
    val state:State<CoinDetailsState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            coinId->getCoin(coinId
        )
        }
    }
    private fun getCoin(coinId:String){
        getspecificcoinUsecase(coinId).onEach { result->
            when(result){
                is Resource.Success->{
                     _state.value= CoinDetailsState(coin = result.data)
                }
                is Resource.Error->{
                     _state.value= CoinDetailsState(error = result.message?:"An unexpected error occurred")
                }
                is Resource.Loading->{
                    _state.value= CoinDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}