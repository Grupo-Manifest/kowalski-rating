@file:OptIn(ExperimentalMaterial3Api::class)

package ecb.manifest.kowalski.rating.ui.presentation.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ecb.manifest.kowalski.rating.events.ReviewEvent
import ecb.manifest.kowalski.rating.events.ReviewState

@Composable
fun ReviewScreen(state: ReviewState, onEvent: (ReviewEvent) -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ReviewEvent.ShowReviewDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Review",
                )
            }
        }
    ) { padding ->
        if (state.isWritingReview) {
            AddReviewDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.reviews) { review ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "${review.id}", fontSize = 12.sp)
                        Text(
                            text = "${review.serviceQuality}",
                            fontSize = 20.sp,
                        )
                    }
                    IconButton(onClick = {
                        onEvent(ReviewEvent.DeleteReview(review))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete review",
                        )
                    }
                }
            }
        }
    }
}