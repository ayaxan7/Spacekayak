package com.ayaan.spacekayak.screens.components.componentnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayaan.spacekayak.screens.components.SpaceKayakButton

@Composable
fun OtpVerificationPage(
    phoneNumber: String,
    onClose: () -> Unit,
    onBack: () -> Unit,
    onVerify: (String) -> Unit,
    shouldShowError: Boolean = false,
    onErrorShown: () -> Unit = {}
) {
    var otpValue by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    // Watch for external error trigger
    LaunchedEffect(shouldShowError) {
        if (shouldShowError) {
            isError = true
            onErrorShown()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF001A3D))
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {

        // Drag Handle
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(5.dp)
                    .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(3.dp))
            )
        }

        // Back Button
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .size(48.dp)
                .background(Color.White.copy(alpha = 0.1f), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Text(
            text = "Enter the OTP sent\nto your Phone",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            lineHeight = 40.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "We sent a 6 digit code to ",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.6f)
            )
            Text(
                text = "+91 $phoneNumber",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.6f),
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color.White.copy(alpha = 0.6f),
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onBack() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Box dimensions
        val configuration = LocalConfiguration.current
        val screenWidthDp = configuration.screenWidthDp.dp
        val totalSpacing = 8.dp * 5
        val availableWidth = screenWidthDp - 48.dp - totalSpacing
        val calculatedBoxSize = availableWidth / 6
        val boxSize = minOf(calculatedBoxSize, 72.dp)
        val fontSize = minOf(boxSize.value * 0.4f, 28f).sp

        // OTP Input Field using BasicTextField with decorationBox
        BasicTextField(
            value = otpValue,
            onValueChange = { newValue ->
                // Only allow digits and max 6 characters
                if (newValue.length <= 6 && newValue.all { it.isDigit() }) {
                    otpValue = newValue
                    // Clear error when user starts typing again
                    if (isError) {
                        isError = false
                    }
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            singleLine = true,
            decorationBox = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(6) { index ->
                        val isFocused = index == otpValue.length && !isError
                        val char = otpValue.getOrNull(index)?.toString() ?: ""

                        Box(
                            modifier = Modifier
                                .size(boxSize)
                                .background(
                                    color = if (isError) Color(0xFFFFE5E5) else Color.White,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .then(
                                    if (isFocused)
                                        Modifier.border(
                                            width = 2.dp,
                                            color = Color(0xFF3B9EFF),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                    else if (isError && char.isNotEmpty())
                                        Modifier.border(
                                            width = 2.dp,
                                            color = Color(0xFFFF4444),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                    else Modifier
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char,
                                fontSize = fontSize,
                                color = if (isError) Color(0xFFFF4444) else Color.Black,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Error Message
        if (isError) {
            Text(
                text = "Invalid OTP. Please try again.",
                fontSize = 14.sp,
                color = Color(0xFFFF4444),
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

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
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.clickable {
                    // Clear OTP and error on resend
                    otpValue = ""
                    isError = false
                }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Verify Button
        SpaceKayakButton(
            text = "Verify my number",
            onClick = {
                if (otpValue.length == 6) {
                    onVerify(otpValue)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}