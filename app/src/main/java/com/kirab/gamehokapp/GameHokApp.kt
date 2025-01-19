package com.kirab.gamehokapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kirab.gamehokapp.view.detailScreen.DetailsScreen
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

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            HomeScreen(
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
        ) {
            composable(
                route = Screens.TournamentDetail.route,
                arguments = listOf(navArgument("tournamentId") { type = NavType.StringType })
            ) { backStackEntry ->
                val tournamentId = backStackEntry.arguments?.getString("tournamentId")
                // Get the tournament info based on ID
                val tournament = remember {
                    TournamentInfo(
                        game = "PUBG tournament",
                        organizer = "Red Bull",
                        registrationStatus = RegistrationStatus.OPEN,
                        currentPlayers = 670,
                        maxPlayers = 800,
                        gameName = "BGMI",
                        gameMode = "Solo",
                        entryFee = 10,
                        startTime = "3rd Aug at 10:00 pm",
                        prizePool = 1000,
                        backgroundImage = R.drawable.pubg_tournament,
                        organizerLogo = R.drawable.redbull
                    )
                }

                DetailsScreen(
                    tournamentInfo = tournament,
                    onBackClick = { navController.popBackStack() },
                    onShareClick = { /* Handle share functionality */ }
                )
            }
        }
    }
}