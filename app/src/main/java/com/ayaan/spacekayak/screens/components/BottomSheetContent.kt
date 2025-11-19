package com.ayaan.spacekayak.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ayaan.spacekayak.screens.components.componentnavigation.BottomSheetPage
import com.ayaan.spacekayak.screens.components.componentnavigation.OtpVerificationPage
import com.ayaan.spacekayak.screens.components.componentnavigation.PhoneVerificationPage
import com.ayaan.spacekayak.screens.components.componentnavigation.SuccessVerificationPage
import kotlin.random.Random

@Composable
fun BottomSheetContent(onClose: () -> Unit, onContinue: () -> Unit) {
    var currentPage by remember { mutableStateOf<BottomSheetPage>(BottomSheetPage.PhoneVerification) }
    var phoneNumber by remember { mutableStateOf("") }
    var showOtpError by remember { mutableStateOf(false) }

    when (val page = currentPage) {
        is BottomSheetPage.PhoneVerification -> {
            PhoneVerificationPage(
                phoneNumber = phoneNumber,
                onPhoneNumberChange = { phoneNumber = it },
                onClose = onClose,
                onContinue = {
                    if (phoneNumber.length == 10) {
                        currentPage = BottomSheetPage.OtpVerification(phoneNumber)
                        showOtpError = false // Reset error when moving to OTP page
                    }
                }
            )
        }

        is BottomSheetPage.OtpVerification -> {
            OtpVerificationPage(
                phoneNumber = page.phoneNumber,
                onClose = onClose,
                onBack = {
                    currentPage = BottomSheetPage.PhoneVerification
                    showOtpError = false
                },
                onVerify = { enteredOtp ->
                    // Simulate random validation (50% success rate for demo)
                    val randomValue = Random.nextFloat()

                    if (randomValue < 0.5) {
                        // Success - navigate to success screen
                        currentPage = BottomSheetPage.Success
                        showOtpError = false
                    } else {
                        // Error - trigger error state
                        showOtpError = true
                    }
                },
                shouldShowError = showOtpError,
                onErrorShown = {
                    // Reset the trigger after showing error
                    showOtpError = false
                }
            )
        }

        is BottomSheetPage.Success -> {
            SuccessVerificationPage(
                onAnimationComplete = {
                    onContinue()
                }
            )
        }
    }
}