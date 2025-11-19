package com.ayaan.spacekayak.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayaan.spacekayak.R
import com.ayaan.spacekayak.screens.components.BottomSheetContent
import com.ayaan.spacekayak.screens.components.SpaceKayakButton
import com.ayaan.spacekayak.navigation.Route
import com.ayaan.spacekayak.ui.theme.BottomSheetBlue
import com.ayaan.spacekayak.ui.theme.CircleBlue
import com.ayaan.spacekayak.ui.theme.SkyBlue
import com.ayaan.spacekayak.ui.theme.TextDeepBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TC(navController: NavController) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SkyBlue,
                        Color.White
                    )
                )
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Color.Transparent
                )
                .padding(top = 32.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Shield Logo Placeholder
            Image(
                painter = painterResource(id = R.drawable.big_lock),
                contentDescription = "Shield Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 4.dp)
            )

            // Main Heading
            Text(
                text = "Private by Design.",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Text(
                text = "You're in Control.",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Subtitle
            Text(
                text = "Shield protects you from scams, risky links, and harmful apps, all while keeping your data private and secure.",
                fontSize = 16.sp,
                color = TextDeepBlue.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            // Feature 1
            FeatureRow(
                iconRes = R.drawable.lock,
                text = "All checks happen on your phone; contacts and chats stay private."
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Feature 2
            FeatureRow(
                iconRes = R.drawable.shield,
                text = "We request only permissions needed to keep you safe."
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Feature 3
            FeatureRow(
                iconRes = R.drawable.clock,
                text = "Control your data access anytime.We never sell your information."
            )
            Spacer(modifier = Modifier.height(48.dp))

            SpaceKayakButton(
                text = "I Understand",
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    }

    // Bottom Sheet
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
            containerColor = BottomSheetBlue,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            dragHandle = null
        ) {
            BottomSheetContent(
                onClose = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                },
                onContinue = {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                            navController.navigate(Route.Login.path) {
                                launchSingleTop = true
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun FeatureRow(iconRes: Int, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = CircleBlue.copy(alpha = .16f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = TextDeepBlue,
            lineHeight = 22.sp,
        )
    }
}