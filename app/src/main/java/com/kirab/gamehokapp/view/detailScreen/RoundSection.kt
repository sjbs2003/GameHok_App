package com.kirab.gamehokapp.view.detailScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kirab.gamehokapp.ui.theme.GamehokTheme
import com.kirab.gamehokapp.view.homeScreen.TournamentInfo

data class RoundInfo(
    val roundNumber: Int,
    val eliminationType: String,
    val nextRoundCount: Int,
    val dateTime: String
)

@Composable
fun RoundsAndScheduleSection(
    tournamentInfo: TournamentInfo,
    modifier: Modifier = Modifier
) {
    val eliminationType = when(tournamentInfo.gameName) {
        "PUBG" -> "Single Elimination"
        "COD" -> "Squad Elimination"
        else -> "Team Elimination"
    }

    val rounds = listOf(
        RoundInfo(1, eliminationType, 4, "3rd Aug, 10:00 pm"),
        RoundInfo(2, eliminationType, 4, "3rd Aug, 10:00 pm"),
        RoundInfo(3, eliminationType, 4, "3rd Aug, 10:00 pm")
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = GamehokTheme.DarkBackground
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(
                text = "Rounds and Schedule",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            rounds.forEach { round ->
                RoundItem(round = round)
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    color = Color.White.copy(alpha = 0.1f)
                )
            }
        }
    }
}

@Composable
private fun RoundItem(
    round: RoundInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Qualifier and Elimination Type Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Qualifiers (Round ${round.roundNumber})",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )

            Surface(
                color = Color(0xFF673AB7),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = round.eliminationType,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }

        // Top 4 and DateTime Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Top ${round.nextRoundCount} to next round",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.7f)
            )

            Text(
                text = round.dateTime,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.7f)
            )
        }
    }
}