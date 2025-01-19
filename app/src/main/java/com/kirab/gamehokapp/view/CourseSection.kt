package com.kirab.gamehokapp.view

import  androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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

data class CourseInfo(
    val title: String,
    val description: String,
    val instructor: String,
    val duration: String,
    val thumbnail: Int
)

@Composable
fun CourseSection(
    modifier: Modifier = Modifier,
    onViewAllClick: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapperFlingBehavior(lazyListState)

    // Calculate current page for dot indicators
    val currentPage = remember {
        derivedStateOf {
            val firstVisibleItem = lazyListState.firstVisibleItemIndex
            val centerOffset = lazyListState.firstVisibleItemScrollOffset
            val itemSize = lazyListState.layoutInfo.visibleItemsInfo.firstOrNull()?.size ?: 0
            if (centerOffset > itemSize / 2) firstVisibleItem + 1 else firstVisibleItem
        }
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
                text = "Learn from the best to be the best",
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

        // Course Cards
        LazyRow(
            state = lazyListState,
            flingBehavior = flingBehavior,
            modifier = modifier.snapToCenter(lazyListState),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(3) { index ->
                Box(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(end = if (index < 2) 16.dp else 0.dp)
                ) {
                    CourseCard(
                        courseInfo = when(index) {
                            0 -> CourseInfo(
                                title = "Esports for Beginners",
                                description = "Start your journey with experts and be the professional you want",
                                instructor = "By Ninja",
                                duration = "16 Mins",
                                thumbnail = R.drawable.course_thumbnail1
                            )
                            1 -> CourseInfo(
                                title = "Advanced PUBG Tactics",
                                description = "Master advanced strategies and gameplay techniques",
                                instructor = "By Shroud",
                                duration = "25 Mins",
                                thumbnail = R.drawable.course_thumbnail2
                            )
                            else -> CourseInfo(
                                title = "Pro Gaming Mindset",
                                description = "Develop the mental strength needed for competitive gaming",
                                instructor = "By Pokimane",
                                duration = "20 Mins",
                                thumbnail = R.drawable.course_thumbnail3
                            )
                        }
                    )
                }
            }
        }

        // Dot indicators
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(8.dp)
                        .background(
                            color = if (currentPage.value == index)
                                MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Composable
fun CourseCard(
    courseInfo: CourseInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF1F1FA)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Thumbnail
                Image(
                    painter = painterResource(id = courseInfo.thumbnail),
                    contentDescription = courseInfo.title,
                    modifier = Modifier
                        .size(110.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                // Course Information
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = courseInfo.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = courseInfo.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = courseInfo.instructor,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Bottom section with duration and start button
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "Duration",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = courseInfo.duration,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.clickable { /* Handle start course click */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.start),
                        contentDescription = "Start Course",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Start Course",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}