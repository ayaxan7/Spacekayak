package com.ayaan.spacekayak

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayaan.spacekayak.ui.theme.TextBlue
import kotlinx.coroutines.launch
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun OnboardingScreen(navController: NavController, modifier: Modifier = Modifier) {
    val images = listOf(
        R.drawable.onboarding,
        R.drawable.onboarding_1,
        R.drawable.onboarding_2,
    )

    val pagerState = rememberPagerState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Image Pager
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(images[page]),
                contentDescription = "Onboarding page ${page + 1}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Bottom section with indicators and button
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Page indicators
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.padding(bottom = 24.dp),
                activeColor = Color.White,
                inactiveColor = Color.White.copy(alpha = 0.5f),
                indicatorWidth = 8.dp,
                indicatorHeight = 8.dp,
                spacing = 8.dp
            )
            val scope=rememberCoroutineScope()
            // Next button
            Button(
                onClick = {
                    if (pagerState.currentPage < images.size - 1) {
                        // Move to next page
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        // Navigate to main screen
                        navController.navigate(Route.Login.path) {
                            popUpTo(Route.Onboarding.path) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = if (pagerState.currentPage < images.size - 1) "Next" else "Get Started",
                    color = TextBlue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}