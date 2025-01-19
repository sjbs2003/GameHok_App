package com.kirab.gamehokapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kirab.gamehokapp.R


@Composable
fun TournamentSection(
    modifier: Modifier = Modifier,
    onViewAllClick: () -> Unit
) {

    val lazyListState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Compete in Battles",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "View All",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF00B167),
                modifier = Modifier.clickable { onViewAllClick() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tournament Cards
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(3) { index ->
                TournamentCard(
                    tournamentInfo = when(index) {
                        0 -> TournamentInfo(
                            game = "PUBG tournament",
                            organizer = "Red Bull",
                            registrationStatus = RegistrationStatus.OPEN,
                            currentPlayers = 670,
                            maxPlayers = 800,
                            gameName = "PUBG",
                            gameMode = "Solo",
                            entryFee = 10,
                            startTime = "3rd Aug at 10:00 pm",
                            prizePool = 1000,
                            backgroundImage = R.drawable.pubg_tournament,
                            organizerLogo = R.drawable.redbull
                        )
                        1 -> TournamentInfo(
                            game = "COD tournament",
                            organizer = "Monster Energy",
                            registrationStatus = RegistrationStatus.CLOSED,
                            currentPlayers = 500,
                            maxPlayers = 500,
                            gameName = "COD",
                            gameMode = "Squad",
                            entryFee = 20,
                            startTime = "4th Aug at 8:00 pm",
                            prizePool = 2000,
                            backgroundImage = R.drawable.cod_tournament,
                            organizerLogo = R.drawable.monster
                        )
                        else -> TournamentInfo(
                            game = "Fortnite tournament",
                            organizer = "Asus",
                            registrationStatus = RegistrationStatus.OPENING_SOON,
                            currentPlayers = 0,
                            maxPlayers = 1000,
                            gameName = "Fortnite",
                            gameMode = "Duo",
                            entryFee = 15,
                            startTime = "5th Aug at 9:00 pm",
                            prizePool = 1500,
                            backgroundImage = R.drawable.fortnite_tournament,
                            organizerLogo = R.drawable.asusrog
                        )
                    }
                )
            }
        }
    }
}

data class TournamentInfo(
    val game: String,
    val organizer: String,
    val registrationStatus: RegistrationStatus,
    val currentPlayers: Int,
    val maxPlayers: Int,
    val gameMode: String,
    val gameName: String,
    val entryFee: Int,
    val startTime: String,
    val prizePool: Int,
    val backgroundImage: Int,
    val organizerLogo: Int
)

enum class RegistrationStatus {
    OPEN,
    CLOSED,
    OPENING_SOON
}

@Composable
fun TournamentCard(
    tournamentInfo: TournamentInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(300.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f)
        )
    ) {
        Column {
            // Image Section
            Box(
                modifier = modifier.fillMaxWidth().height(160.dp)
            ) {
                Image(
                    painter = painterResource(id = tournamentInfo.backgroundImage),
                    contentDescription = tournamentInfo.game,
                    modifier = modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Registration Status and Player Count
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Surface(
                        color = when(tournamentInfo.registrationStatus) {
                            RegistrationStatus.OPEN -> Color(0xFF00B167)
                            RegistrationStatus.CLOSED -> Color.Red
                            RegistrationStatus.OPENING_SOON -> Color.Gray
                        },
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = when(tournamentInfo.registrationStatus) {
                                RegistrationStatus.OPEN -> "Registration Open"
                                RegistrationStatus.CLOSED -> "Registration Closed"
                                RegistrationStatus.OPENING_SOON -> "Registration Opening Soon"
                            },
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White,
                            modifier = modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }

                    Surface(
                        color = Color.Black.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(
                            modifier = modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Players",
                                tint = Color.White,
                                modifier = modifier.size(16.dp)
                            )
                            Text(
                                text = "${tournamentInfo.currentPlayers}/${tournamentInfo.maxPlayers}",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White
                            )
                        }
                    }
                }

                // Organizer Logo
                Image(
                    painter = painterResource(id = tournamentInfo.organizerLogo),
                    contentDescription = tournamentInfo.organizer,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .background(Color.White, CircleShape)
                )
            }

            // Tournament Details Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E1E1E))
                    .padding(16.dp)
            ) {
                Text(
                    text = tournamentInfo.game,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

                Text(
                    text = "By ${tournamentInfo.organizer}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Game Mode and Entry Fee
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // BGMI Box
                    Surface(
                        color = Color(0xFF2A2A2A),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = tournamentInfo.gameName,
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }

                    // Game Mode Box
                    Surface(
                        color = Color(0xFF2A2A2A),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = tournamentInfo.gameMode,
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }

                    // Entry Fee Box
                    Surface(
                        color = Color(0xFF2A2A2A),
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
                                contentDescription = "coins",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Start Time
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "time",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Starts ${tournamentInfo.startTime}",
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Prize Pool
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.trophy),
                        contentDescription = "prize",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Prize Pool- ${tournamentInfo.prizePool}",
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.coin),
                        contentDescription = "coins",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
