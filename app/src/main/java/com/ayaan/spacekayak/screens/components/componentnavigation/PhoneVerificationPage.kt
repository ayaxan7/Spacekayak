package com.ayaan.spacekayak.screens.components.componentnavigation

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayaan.spacekayak.screens.components.SpaceKayakButton
import com.ayaan.spacekayak.ui.theme.BottomSheetBlue
import com.ayaan.spacekayak.ui.theme.ButtonBlue

@Composable
fun PhoneVerificationPage(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onClose: () -> Unit,
    onContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BottomSheetBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Drag Handle at top center
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

        // Close Button
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd
        ) {
            IconButton(
                onClick = onClose,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.1f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Title
        Text(
            text = "Verify your\nphone number",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            lineHeight = 42.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Description
        Text(
            text = "We're verifying your number securely.\nAn OTP may be sent if needed.",
            fontSize = 15.sp,
            color = Color.White.copy(alpha = 0.7f),
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Phone Input Field
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
            ) {
                // Country Code
                Text(
                    text = "+91",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .background(
                            Color.LightGray, RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 4.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Vertical Divider
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(24.dp)
                        .background(Color.LightGray)
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Phone Number Input
                BasicTextField(
                    value = phoneNumber,
                    onValueChange = { newValue ->
                        if (newValue.length <= 10) {
                            onPhoneNumberChange(newValue.filter { it.isDigit() })
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 16.sp, color = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    decorationBox = { innerTextField ->
                        if (phoneNumber.isEmpty()) {
                            Text(
                                text = "Enter Phone Number", fontSize = 16.sp, color = Color.Gray
                            )
                        }
                        innerTextField()
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Continue Button
        SpaceKayakButton(
            text = "Continue", onClick = onContinue
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Terms Text
        val annotatedText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)) {
                append("By continuing, I confirm I am at least 18 years old and agree to Shield's ")
            }
            withStyle(
                style = SpanStyle(
                    color = ButtonBlue,
                    fontSize = 13.sp,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Terms")
            }
            withStyle(style = SpanStyle(color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)) {
                append(" and ")
            }
            withStyle(
                style = SpanStyle(
                    color = ButtonBlue,
                    fontSize = 13.sp,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Privacy Policy")
            }
            withStyle(style = SpanStyle(color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)) {
                append(", and\nreceiving SMS alerts.")
            }
        }

        Text(
            text = annotatedText,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}