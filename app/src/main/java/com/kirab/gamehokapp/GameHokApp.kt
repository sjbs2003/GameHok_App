package com.kirab.gamehokapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kirab.gamehokapp.view.tournamentDetailScreen.TournamentDetailScreen
import com.kirab.gamehokapp.view.homeScreen.HomeScreen
import com.kirab.gamehokapp.view.homeScreen.RegistrationStatus
import com.kirab.gamehokapp.view.homeScreen.TournamentInfo

enum class Screens(val route: String) {
    Home("home"),
    TournamentDetail("tournament/{tournamentId}")
}

@Composable
fun GameHokApp() {
    val navController = rememberNavController()

    // Create a list of sample tournaments that we'll use across screens
    val tournaments = remember {
        listOf(
            TournamentInfo(
                id = "1",
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
                id = "2",
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
                id = "3",
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

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            HomeScreen(
                tournaments = tournaments,
                onTournamentClick = { tournamentId ->
                    navController.navigate(
                        Screens.TournamentDetail.route.replace(
                            "{tournamentId}",
                            tournamentId
                        )
                    )
                },
                modifier = Modifier
            )
        }

        composable(
            route = Screens.TournamentDetail.route,
            arguments = listOf(
                navArgument("tournamentId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val tournamentId = backStackEntry.arguments?.getString("tournamentId")
            val tournament = remember(tournamentId) {
                tournaments.find { it.id == tournamentId }
                    ?: tournaments[0]
            }
            TournamentDetailScreen(
                tournamentInfo = tournament,
                onBackClick = { navController.popBackStack() },
                onShareClick = {}
            )
        }
    }
}