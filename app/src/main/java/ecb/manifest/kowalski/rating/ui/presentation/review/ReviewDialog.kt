package ecb.manifest.kowalski.rating.ui.presentation.review

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ecb.manifest.kowalski.rating.events.ReviewEvent
import ecb.manifest.kowalski.rating.events.ReviewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewDialog(
    state: ReviewState,
    onEvent: (ReviewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(ReviewEvent.HideReviewDialog) },
        title = { Text(text = "Add review") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = state.serviceQuality,
                    onValueChange = {
                        onEvent(ReviewEvent.SetServiceQuality(it))
                    },
                    placeholder = { Text(text = "Service Quality") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onEvent(ReviewEvent.SaveReview) }) {
                Text(text = "Confirm")
            }
        },
    )
}
