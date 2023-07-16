package com.example.cryptowell.domain.use_case.get_coins

import com.example.cryptowell.common.Resource
import com.example.cryptowell.data.remote.dto.toCoin
import com.example.cryptowell.domain.model.Coin
import com.example.cryptowell.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoins_usecase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke():Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins=repository.getCoins().map { it.toCoin()}
            emit(Resource.Success<List<Coin>>(coins))
        }
        catch (e:HttpException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage?:"An unexpected error occurred"))
        }
        catch (e:IOException){
            emit(Resource.Error<List<Coin>>("Retry with an internet connection"))
        }
    }
}