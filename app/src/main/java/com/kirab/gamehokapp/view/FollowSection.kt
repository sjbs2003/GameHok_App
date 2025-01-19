package com.kirab.gamehokapp.view

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kirab.gamehokapp.R

data class Gamer(
    val id: Int,
    val name: String,
    val image: Int
)

@Composable
fun PeopleToFollowSection(
    modifier: Modifier = Modifier,
    onViewMoreClick: () -> Unit
) {
    val gamers = remember {
        listOf(
            Gamer(1, "Ninja", R.drawable.gamer1),
            Gamer(2, "Shroud", R.drawable.gamer2),
            Gamer(3, "PewDiePie", R.drawable.gamer3),
            Gamer(4, "Pokimane", R.drawable.gamer4),
            Gamer(5, "DrDisRespect", R.drawable.gamer5)
        )
    }

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
                text = "People to follow",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "View More",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF00B167),
                modifier = Modifier.clickable { onViewMoreClick() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Gamers List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),  // Add fixed height constraint
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(gamers) { gamer ->
                GamerCard(gamer = gamer)
            }
        }
    }
}

@Composable
fun GamerCard(
    gamer: Gamer,
    modifier: Modifier = Modifier
) {
    var isFollowing by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            Image(
                painter = painterResource(id = gamer.image),
                contentDescription = "Profile picture of ${gamer.name}",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            // Gamer Name
            Text(
                text = gamer.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        // Follow Button
        val buttonWidthState = animateDpAsState(
            targetValue = if (isFollowing) 120.dp else 100.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "button width"
        )

        Button(
            onClick = { isFollowing = !isFollowing },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFollowing)
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .width(buttonWidthState.value)
                .height(36.dp)
        ) {
            Text(
                text = if (isFollowing) "Following" else "Follow",
                color = if (isFollowing)
                    MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}