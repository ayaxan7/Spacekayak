package com.ayaan.spacekayak.screens.components.componentnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayaan.spacekayak.screens.components.SpaceKayakButton

@Composable
fun OtpVerificationPage(
    phoneNumber: String, onClose: () -> Unit, onBack: () -> Unit, onVerify: () -> Unit
) {
    var otpValue by remember { mutableStateOf("") }
    val otpLength = 6
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val totalSpacing = 8.dp * (otpLength - 1)
    val availableWidth = screenWidthDp - 48.dp - totalSpacing
    val boxSize = minOf(availableWidth / otpLength, 72.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF001A3D))
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {

        // Drag handle
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(2.dp))
            )
        }

        // Back Button
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .size(40.dp)
                .background(Color.White.copy(alpha = 0.1f), CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "Enter the OTP sent\nto your Phone",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            lineHeight = 40.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Description row
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "We sent a 6 digit code to ",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.6f)
            )
            Text(
                text = "+91 $phoneNumber",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f),
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color.White.copy(alpha = 0.7f),
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onBack() })
        }

        Spacer(modifier = Modifier.height(28.dp))

        // OTP FIELD ROW
        BasicTextField(
            value = otpValue,
            onValueChange = { newValue ->
                if (newValue.length <= otpLength && newValue.all { it.isDigit() }) otpValue =
                    newValue
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            decorationBox = { innerTextField ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeat(otpLength) { index ->
                        Box(
                            modifier = Modifier
                                .size(boxSize)
                                .background(Color.White, RoundedCornerShape(16.dp))
                                .border(
                                    width = if (otpValue.length == index) 2.dp else 1.dp,
                                    color = if (otpValue.length == index) Color(0xFF3B9EFF)
                                    else Color(0xFFDDDDDD),
                                    shape = RoundedCornerShape(16.dp)
                                ), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = otpValue.getOrNull(index)?.toString() ?: "",
                                fontSize = (boxSize.value * 0.4f).sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }
                    }

                    Box(modifier = Modifier.size(0.dp)) { innerTextField() }
                }
            })

        Spacer(modifier = Modifier.height(24.dp))

        // Resend OTP
        Column {
            Text(
                text = "Didn't receive the code?",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Resend Code",
                fontSize = 15.sp,
                color = Color(0xFF3B9EFF),
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { /* resend */ })
        }

        Spacer(modifier = Modifier.height(32.dp))

        // VERIFY BUTTON
        SpaceKayakButton(
            text = "Verify", onClick = onVerify
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

