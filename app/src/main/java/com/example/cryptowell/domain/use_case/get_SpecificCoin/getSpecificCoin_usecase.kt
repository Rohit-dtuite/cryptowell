package com.example.cryptowell.domain.use_case.get_SpecificCoin

import com.example.cryptowell.common.Resource
import com.example.cryptowell.data.remote.dto.toCoin
import com.example.cryptowell.data.remote.dto.toCoinDetail
import com.example.cryptowell.domain.model.Coin
import com.example.cryptowell.domain.model.CoinDetail
import com.example.cryptowell.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSpecificCoin_usecase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId:String):Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin=repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        }
        catch (e:HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage?:"An unexpected error occurred"))
        }
        catch (e:IOException){
            emit(Resource.Error<CoinDetail>("Retry with an internet connection"))
        }
    }
}