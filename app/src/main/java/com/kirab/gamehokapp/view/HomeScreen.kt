package com.kirab.gamehokapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            TopBar()

            LazyColumn(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth(),
                state = rememberLazyListState(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                item { PremiumCardsList() }
                item { GameSection(onViewAllClick = {}) }
                item { TournamentSection(onViewAllClick = {}) }
                item { CourseSection(onViewAllClick = {}) }
                item { PeopleToFollowSection(onViewMoreClick = {}) }
            }

            BottomBar()
        }
    }
}


