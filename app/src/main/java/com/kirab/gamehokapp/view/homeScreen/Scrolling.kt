package com.kirab.gamehokapp.view.homeScreen

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun rememberSnapperFlingBehavior(
    lazyListState: LazyListState,
    snapOffset: Dp = 0.dp
): FlingBehavior {
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    return remember(lazyListState) {
        object : FlingBehavior {
            override suspend fun ScrollScope.performFling(initialVelocity: Float): Float {
                val item = lazyListState.layoutInfo.visibleItemsInfo.firstOrNull() ?: return initialVelocity

                val snapOffsetPx = with(density) { snapOffset.toPx() }
                val itemSize = item.size
                val currentOffset = lazyListState.firstVisibleItemScrollOffset

                // Calculate target item based on velocity and current offset
                val targetItem = when {
                    initialVelocity > 0 -> lazyListState.firstVisibleItemIndex + 1
                    initialVelocity < 0 -> lazyListState.firstVisibleItemIndex - 1
                    currentOffset > itemSize / 2 -> lazyListState.firstVisibleItemIndex + 1
                    else -> lazyListState.firstVisibleItemIndex
                }.coerceIn(0, lazyListState.layoutInfo.totalItemsCount - 1)

                scope.launch {
                    lazyListState.animateScrollToItem(
                        targetItem,
                        snapOffsetPx.toInt()
                    )
                }

                return 0f // Consume the fling
            }
        }
    }
}

@Composable
fun rememberSnapperLayoutInfo(
    lazyListState: LazyListState,
    snapOffset: Dp = 0.dp
): SnapperLayoutInfo {
    val density = LocalDensity.current
    val snapOffsetPx = with(density) { snapOffset.toPx() }

    return remember(lazyListState, snapOffsetPx) {
        object : SnapperLayoutInfo {
            override fun distanceToIndexSnap(index: Int): Int {
                val itemInfo = lazyListState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
                    ?: return 0
                return itemInfo.offset - snapOffsetPx.toInt()
            }

            override val currentItem: Int
                get() = lazyListState.firstVisibleItemIndex
        }
    }
}

interface SnapperLayoutInfo {
    fun distanceToIndexSnap(index: Int): Int
    val currentItem: Int
}

// Extension function for setting up snapping behavior
fun Modifier.snapToCenter(
    lazyListState: LazyListState,
    snapOffset: Dp = 0.dp
): Modifier = composed {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val snapOffsetPx = with(density) { snapOffset.toPx() }

    nestedScroll(remember(lazyListState, snapOffsetPx) {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return Offset.Zero
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                val layout = lazyListState.layoutInfo
                val visibleItems = layout.visibleItemsInfo
                if (visibleItems.isEmpty()) return Velocity.Zero

                val itemSize = visibleItems[0].size
                val currentOffset = lazyListState.firstVisibleItemScrollOffset

                val targetIndex = if (currentOffset > itemSize / 2) {
                    lazyListState.firstVisibleItemIndex + 1
                } else {
                    lazyListState.firstVisibleItemIndex
                }

                scope.launch {
                    lazyListState.animateScrollToItem(targetIndex, snapOffsetPx.toInt())
                }

                return Velocity.Zero
            }
        }
    })
}