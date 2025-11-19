package com.ayaan.spacekayak

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayaan.spacekayak.components.SpaceKayakButton
import com.ayaan.spacekayak.ui.theme.ButtonBlue
import com.ayaan.spacekayak.ui.theme.CircleBlue
import com.ayaan.spacekayak.ui.theme.InnerButtonBlue
import com.ayaan.spacekayak.ui.theme.SkyBlue
import com.ayaan.spacekayak.ui.theme.TextDeepBlue

@Composable
fun TC(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SkyBlue, // Sky blue
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
                .padding(top=32.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),
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
                text="I Understand",
                onClick = {
                    navController.navigate(Route.Login.path){

                        launchSingleTop=true
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
//            modifier = Modifier.padding( 12.dp)
        )
    }
}