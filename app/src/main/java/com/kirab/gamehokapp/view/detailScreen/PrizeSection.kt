package com.kirab.gamehokapp.view.detailScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kirab.gamehokapp.R
import com.kirab.gamehokapp.ui.theme.GamehokTheme

data class PrizeInfo(
    val position: String,
    val amount: Int
)

@Composable
fun TournamentPrizeSection(
    totalPrize: Int,
    prizes: List<PrizeInfo>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = GamehokTheme.PrizeGreen
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Total Prize Row
            PrizeRow(
                title = "Total Tournament Prize",
                amount = totalPrize,
                isTotal = true
            )

            // Divider after total
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                color = Color.White.copy(alpha = 0.1f)
            )

            // Individual prize rows
            prizes.forEachIndexed { index, prize ->
                PrizeRow(
                    title = prize.position,
                    amount = prize.amount
                )

                if (index < prizes.size - 1) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        color = Color.White.copy(alpha = 0.1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun PrizeRow(
    modifier: Modifier = Modifier,
    title: String,
    amount: Int,
    isTotal: Boolean = false
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (!isTotal) {
                Icon(
                    painter = painterResource(id = R.drawable.trophy),
                    contentDescription = null,
                    tint = Color(0xFFFFD700), // Gold color for trophy
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal,
                color = Color.White
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = amount.toString(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal,
                color = Color.White
            )
            Icon(
                painter = painterResource(id = R.drawable.coin),
                contentDescription = "coins",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}