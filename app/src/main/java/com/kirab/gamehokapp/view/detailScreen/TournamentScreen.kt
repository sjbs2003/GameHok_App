package com.kirab.gamehokapp.view.detailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kirab.gamehokapp.R
import com.kirab.gamehokapp.ui.theme.GamehokTheme
import com.kirab.gamehokapp.view.homeScreen.GameHokImage
import com.kirab.gamehokapp.view.homeScreen.TournamentInfo

@Composable
fun DetailsScreen(
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
        // Tournament Banner Image with Overlay
        Box(modifier = Modifier.fillMaxWidth()) {
            GameHokImage(
                imageRes = tournamentInfo.backgroundImage,
                contentDescription = tournamentInfo.game,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(300.dp)
            )

            // Top Bar with Back and Share buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Back Button
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                // Share Button
                IconButton(
                    onClick = onShareClick,
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = Color.White
                    )
                }
            }

            // Tournament Info Overlay at the bottom of the image
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.7f))
                    .padding(16.dp)
            ) {
                Column {
                    // Players Count and Registration Time
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = Color.Black.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Players",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "${tournamentInfo.currentPlayers}/${tournamentInfo.maxPlayers}",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.White
                                )
                            }
                        }

                        Text(
                            text = "Registration Closes in 2d 15h 10m",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tournament Title and Organizer
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = tournamentInfo.game,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "By ${tournamentInfo.organizer}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }

                        // Organizer Logo
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.White, CircleShape)
                                .padding(4.dp)
                        ) {
                            GameHokImage(
                                imageRes = tournamentInfo.organizerLogo,
                                contentDescription = "Organizer Logo",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Game Tags
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            color = GamehokTheme.DarkBackground,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = tournamentInfo.gameName,
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }

                        Surface(
                            color = GamehokTheme.DarkBackground,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Entry-${tournamentInfo.entryFee}",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.coin),
                                    contentDescription = "Entry fee",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}