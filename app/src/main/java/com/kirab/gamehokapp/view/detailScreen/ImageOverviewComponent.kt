package com.kirab.gamehokapp.view.detailScreen

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kirab.gamehokapp.R
import com.kirab.gamehokapp.ui.theme.GamehokTheme
import com.kirab.gamehokapp.view.homeScreen.GameHokImage
import com.kirab.gamehokapp.view.homeScreen.RegistrationStatus
import com.kirab.gamehokapp.view.homeScreen.TournamentInfo

@Composable
fun TournamentImageOverviewScreen(
    tournamentInfo: TournamentInfo,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GamehokTheme.DarkBackground)
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
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

                // Tournament Info Overlay
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.7f))
                        .padding(16.dp)
                ) {
                    // Registration Time and Players Count
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RegistrationStatusBadge(
                            status = tournamentInfo.registrationStatus
                        )

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

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.White, CircleShape)
                        ) {
                            GameHokImage(
                                imageRes = tournamentInfo.organizerLogo,
                                contentDescription = "Organizer Logo",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
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

            // Navigation Tabs
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GamehokTheme.DarkBackground)
            ) {
                var selectedTab = 0  // 0 for Overview
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val tabs = listOf("Overview", "Players", "Rules")
                    tabs.forEachIndexed { index, tab ->
                        Text(
                            text = tab,
                            color = if (index == selectedTab) Color.White else Color.Gray,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }
                }

                // Bottom divider with indicator
                Box(modifier = Modifier.fillMaxWidth()) {
                    // Gray divider line
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Gray.copy(alpha = 0.2f))
                    )

                    // Green indicator for selected tab
                    Box(
                        modifier = Modifier
                            .width(with(LocalDensity.current) {
                                // Calculate width based on total width divided by number of tabs
                                (LocalConfiguration.current.screenWidthDp.dp - 48.dp) / 3
                            })
                            .height(2.dp)
                            .background(Color(0xFF00B167))
                    )
                }
            }

            // Details Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Details",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Team Size
                DetailItem(
                    icon = R.drawable.ic_social,
                    label = "TEAM SIZE",
                    value = tournamentInfo.gameMode
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Format
                DetailItem(
                    icon = R.drawable.format,
                    label = "FORMAT",
                    value = "Single Elimination"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tournament Start
                DetailItem(
                    icon = R.drawable.calendar,
                    label = "TOURNAMENT STARTS",
                    value = tournamentInfo.startTime
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Check-in
                DetailItem(
                    icon = R.drawable.clock,
                    label = "CHECK-IN",
                    value = "10 mins before the match starts"
                )
            }
        }
    }
}

@Composable
private fun RegistrationStatusBadge(
    status: RegistrationStatus,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, text) = when (status) {
        RegistrationStatus.OPEN -> Color(0xFF00B167) to "Registration Open • 2hr 32min"
        RegistrationStatus.CLOSED -> Color.Red to "Registration Closed"
        RegistrationStatus.OPENING_SOON -> Color(0xFF9E9E9E) to "Registration Opens in 2d 15h 10m"
    }

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun DetailItem(
    @DrawableRes icon: Int,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xFF00B167),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}