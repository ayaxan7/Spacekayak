package com.ayaan.spacekayak.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayaan.spacekayak.ui.theme.ButtonBlue
import com.ayaan.spacekayak.ui.theme.InnerButtonBlue

@Composable
fun SpaceKayakButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        InnerButtonBlue.copy(alpha = 0.8f),
                        ButtonBlue
                    ),
                    center = Offset.Unspecified,
                    radius = 900f
                ),
                shape = RoundedCornerShape(28.dp)
            )
    ) {
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .fillMaxSize(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(28.dp),
            contentPadding = PaddingValues()
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}