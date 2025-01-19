package com.kirab.gamehokapp.view.homeScreen

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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kirab.gamehokapp.R
import com.kirab.gamehokapp.ui.theme.GamehokTheme
import java.util.UUID


data class TournamentInfo(
    val id: String = UUID.randomUUID().toString(),
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
fun TournamentSection(
    modifier: Modifier = Modifier,
    onViewAllClick: () -> Unit,
    onTournamentClick: (String) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapperFlingBehavior(lazyListState)

    val tournaments = remember {
        listOf(
            TournamentInfo(
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
            ),
            TournamentInfo(
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
            ),
            TournamentInfo(
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
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        val headerText = remember { "Compete in Battles" }

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = headerText,
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
            state = lazyListState,
            flingBehavior = flingBehavior,
            modifier = modifier.snapToCenter(lazyListState),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(
                count = tournaments.size,
                key = { index -> tournaments[index].id }
            ) { index ->
                TournamentCard(
                    tournamentInfo = tournaments[index],
                    onTournamentClick = onTournamentClick,
                    modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null)
                )
            }
        }
    }
}

@Composable
fun TournamentCard(
    tournamentInfo: TournamentInfo,
    onTournamentClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val cardModifier = remember(modifier) {
        modifier.width(300.dp)
    }

    val statusColor = remember(tournamentInfo.registrationStatus) {
        when(tournamentInfo.registrationStatus) {
            RegistrationStatus.OPEN -> Color(0xFF00B167)
            RegistrationStatus.CLOSED -> Color.Red
            RegistrationStatus.OPENING_SOON -> Color.Gray
        }
    }

    Card(
        modifier = cardModifier.clickable { onTournamentClick(tournamentInfo.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f)
        )
    ) {
        Column {
            // Image Section
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                GameHokImage(
                    imageRes = tournamentInfo.backgroundImage,
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
                        color = statusColor,
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
                Box(
                    modifier = Modifier
                        .size(56.dp)  // Increased size
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .background(Color.White, CircleShape)
                ) {
                    GameHokImage(
                        imageRes = tournamentInfo.organizerLogo,
                        contentDescription = tournamentInfo.organizer,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                    )
                }
            }
            // Tournament Detail
            TournamentDetails(
                tournamentInfo = tournamentInfo,
                onTournamentClick = onTournamentClick
            )
        }
    }
}


@Composable
private fun TournamentDetails(
    tournamentInfo: TournamentInfo,
    onTournamentClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(GamehokTheme.TournamentGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Title and organizer
            Text(
                text = tournamentInfo.game,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                color = GamehokTheme.TextWhite
            )

            Text(
                text = "By ${tournamentInfo.organizer}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = GamehokTheme.TextWhite.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Game tags
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    tournamentInfo.gameName,
                    tournamentInfo.gameMode,
                ).forEach { text ->
                    Surface(
                        color = GamehokTheme.DarkBackground,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = text,
                            color = GamehokTheme.TextWhite,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                // Entry fee tag with coin icon
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
                            color = GamehokTheme.TextWhite,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.coin),
                            contentDescription = "coins",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Time and Prize with icons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = "time",
                    tint = GamehokTheme.TextWhite,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Starts ${tournamentInfo.startTime}",
                    color = GamehokTheme.TextWhite,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Prize pool
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.trophy),
                        contentDescription = "prize",
                        tint = Color(0xFFFFD700), // Keeping gold color for trophy
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Prize Pool- ${tournamentInfo.prizePool}",
                        color = GamehokTheme.TextWhite,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.coin),
                        contentDescription = "coins",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Right arrow icon
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "View details",
                    tint = GamehokTheme.TextWhite,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onTournamentClick(tournamentInfo.id) }
                )
            }
        }
    }
}