package com.example.cryptowell.presentation.coin_list.components

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.cryptowell.domain.model.Coin
import com.example.cryptowell.presentation.Screen
import com.example.cryptowell.presentation.coin_details.components.CoinListItem
import com.example.cryptowell.presentation.coin_list.CoinListviewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun CoinListScreen(
    navController: NavHostController,
    viewModel: CoinListviewModel= hiltViewModel()
){
val state=viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()){
        bannerAd(LocalContext.current)
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.coins){
                coin ->
                CoinListItem(coin = coin, onItemclick = {
                    navController.navigate(Screen.CoinDetailScreen.route+"/${coin.id}")
                })
            }
        }
        if (state.error.isNotBlank()){
            Text(text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.Center))
        }
        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
@Composable
fun bannerAd(context: Context){

    AndroidView(
        // on below line specifying width for ads.
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
            // on below line specifying ad view.
            AdView(context).apply {
                // on below line specifying ad size
              setAdSize(AdSize.BANNER)
                // on below line specifying ad unit id
                // currently added a test ad unit id.
                adUnitId = "ca-app-pub-3940256099942544/6300978111"
                // calling load ad to load our ad.
                loadAd(AdRequest.Builder().build())
            }
        }
    )

}