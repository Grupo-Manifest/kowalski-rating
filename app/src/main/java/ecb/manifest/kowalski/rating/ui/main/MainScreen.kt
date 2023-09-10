package ecb.manifest.kowalski.rating.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ecb.manifest.kowalski.rating.events.ReviewEvent
import ecb.manifest.kowalski.rating.ui.navigation.Screen
import ecb.manifest.kowalski.rating.ui.presentation.map.MapScreen
import ecb.manifest.kowalski.rating.ui.presentation.review.AddReviewDialog
import ecb.manifest.kowalski.rating.ui.theme.OrangeShell
import ecb.manifest.kowalski.rating.ui.theme.PurpleShell
import ecb.manifest.kowalski.rating.ui.viewModels.ReviewViewModel

@Composable
fun MainScreen(
    viewModel: ReviewViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController,
    ) {
    val state by viewModel.state.collectAsState()
    val onEvent: (ReviewEvent) -> Unit = viewModel::onEvent

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1.5f)
                .background(color = OrangeShell)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "AVALIAÇÃO",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                )
                Text(
                    text = "Você gostou desse posto?",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        modifier = Modifier
                            .padding(14.dp)
                            .width(100.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(PurpleShell),

                        onClick = { onEvent(ReviewEvent.ShowReviewDialog) }
                    ) {
                        Text(text = "Avaliar")

                        if (state.isWritingReview) {
                            AddReviewDialog(state = state, onEvent = onEvent)
                        }
                    }
                    Button(
                        modifier = Modifier
                            .padding(14.dp)
                            .width(100.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(PurpleShell),

                        onClick = {
                            navController.navigate(Screen.ReviewScreen.route)
                        }
                    ) {
                        Text(text = "Lista")
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(4f)
                .background(color = Color.White)
        ) {
            MapScreen()
        }
    }
}