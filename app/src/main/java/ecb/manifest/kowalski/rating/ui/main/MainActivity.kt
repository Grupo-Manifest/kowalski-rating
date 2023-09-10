package ecb.manifest.kowalski.rating.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import ecb.manifest.kowalski.rating.ui.presentation.review.ReviewScreen
import ecb.manifest.kowalski.rating.ui.theme.OrangeShell
import ecb.manifest.kowalski.rating.ui.theme.PurpleShell

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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

                                onClick = { /*TODO*/ }
                            ) {
                                Text(text = "Rating")
                            }
                            Button(
                                modifier = Modifier
                                    .padding(14.dp)
                                    .width(100.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(PurpleShell),

                                onClick = { /*TODO*/ }
                            ) {
                                Text(text = "List")
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
                    ReviewScreen()
                }
            }
        }
    }
}