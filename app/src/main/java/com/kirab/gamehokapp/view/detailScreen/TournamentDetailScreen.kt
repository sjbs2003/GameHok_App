package com.kirab.gamehokapp.view.detailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kirab.gamehokapp.ui.theme.GamehokTheme
import com.kirab.gamehokapp.view.homeScreen.TournamentInfo

@Composable
fun TournamentDetailScreen(
    tournamentInfo: TournamentInfo,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GamehokTheme.DarkBackground)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                TournamentImageOverviewScreen(
                    tournamentInfo = tournamentInfo,
                    onBackClick = onBackClick,
                    onShareClick = onShareClick
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                val prizes = listOf(
                    PrizeInfo("1st Prize", 1000),
                    PrizeInfo("2nd Prize", 500),
                    PrizeInfo("3rd Prize", 200),
                    PrizeInfo("4th Prize", 100),
                    PrizeInfo("5th Prize", 100)
                )
                TournamentPrizeSection(
                    totalPrize = 2000,
                    prizes = prizes,
                    modifier = Modifier.background(GamehokTheme.DarkBackground)
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                RoundsAndScheduleSection(
                    tournamentInfo = tournamentInfo,
                    modifier = Modifier
                        .background(GamehokTheme.DarkBackground)
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}