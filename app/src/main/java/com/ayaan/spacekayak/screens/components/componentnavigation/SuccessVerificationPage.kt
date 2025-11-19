package com.ayaan.spacekayak.screens.components.componentnavigation

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayaan.spacekayak.R
import com.ayaan.spacekayak.ui.theme.BottomSheetBlue
import kotlinx.coroutines.delay

@Composable
fun SuccessVerificationPage(
    onAnimationComplete: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(3000)
        onAnimationComplete()
    }

    Column(
        modifier = Modifier
            .fillMaxHeight(.9f)
            .fillMaxWidth()
            .background(BottomSheetBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Drag Handle
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(5.dp)
                    .background(Color.White.copy(alpha = 0.3f), androidx.compose.foundation.shape.RoundedCornerShape(3.dp))
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.size(160.dp),
            contentAlignment = Alignment.Center
        ) {
                Image(
                    painter = painterResource(R.drawable.phone_verified),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Success text
        Text(
            text = "Your phone is\nverified securely.",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Subtitle
        Text(
            text = "OTP Verified safely with an OTP. Stay alert on\nfake OTP scams.",
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}