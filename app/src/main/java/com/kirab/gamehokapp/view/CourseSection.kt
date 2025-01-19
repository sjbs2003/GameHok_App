package com.kirab.gamehokapp.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kirab.gamehokapp.R
import java.util.UUID

data class CourseInfo(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val instructor: String,
    val duration: String,
    val thumbnail: Int
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CourseSection(
    modifier: Modifier = Modifier,
    onViewAllClick: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapperFlingBehavior(lazyListState)

    // Remember courses list to prevent recreations
    val courses = remember {
        listOf(
            CourseInfo(
                title = "Esports for Beginners",
                description = "Start your journey with experts and be the professional you want",
                instructor = "By Ninja",
                duration = "16 Mins",
                thumbnail = R.drawable.course_thumbnail1
            ),
            CourseInfo(
                title = "Advanced PUBG Tactics",
                description = "Master advanced strategies and gameplay techniques",
                instructor = "By Shroud",
                duration = "25 Mins",
                thumbnail = R.drawable.course_thumbnail2
            ),
            CourseInfo(
                title = "Pro Gaming Mindset",
                description = "Develop the mental strength needed for competitive gaming",
                instructor = "By Pokimane",
                duration = "20 Mins",
                thumbnail = R.drawable.course_thumbnail3
            )
        )
    }

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
        // Header section
        CourseSectionHeader(onViewAllClick)

        Spacer(modifier = Modifier.height(16.dp))

        // Course cards
        LazyRow(
            state = lazyListState,
            flingBehavior = flingBehavior,
            modifier = Modifier.snapToCenter(lazyListState),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(
                count = courses.size,
                key = { index -> courses[index].id }
            ) { index ->
                CourseCard(
                    courseInfo = courses[index],
                    modifier = Modifier
                        .animateItemPlacement()
                        .padding(end = if (index < courses.size - 1) 16.dp else 0.dp)
                )
            }
        }

        // Dot indicators
        DotIndicators(
            totalDots = courses.size,
            currentPage = currentPage.value
        )
    }
}

@Composable
private fun CourseSectionHeader(onViewAllClick: () -> Unit) {
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
}

@Composable
private fun DotIndicators(
    totalDots: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(8.dp)
                    .background(
                        color = if (currentPage == index)
                            MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun CourseCard(
    courseInfo: CourseInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(320.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Top section with image and content
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Left side - Image
                GameHokImage(
                    imageRes = courseInfo.thumbnail,
                    contentDescription = courseInfo.title,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                // Right side - Content
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = courseInfo.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = courseInfo.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black.copy(alpha = 0.7f)
                    )

                    Text(
                        text = courseInfo.instructor,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF00B167)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Divider
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray.copy(alpha = 0.2f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Bottom section with duration and start button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Duration
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "Duration",
                        tint = Color.Black.copy(alpha = 0.7f),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = courseInfo.duration,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black.copy(alpha = 0.7f)
                    )
                }

                // Start Course Button
                Text(
                    text = "Start Course",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF00B167),
                    modifier = Modifier.clickable { /* Handle click */ }
                )
            }
        }
    }
}