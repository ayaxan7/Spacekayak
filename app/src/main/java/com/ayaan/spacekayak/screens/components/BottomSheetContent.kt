package com.ayaan.spacekayak.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ayaan.spacekayak.screens.components.componentnavigation.BottomSheetPage
import com.ayaan.spacekayak.screens.components.componentnavigation.OtpVerificationPage
import com.ayaan.spacekayak.screens.components.componentnavigation.PhoneVerificationPage


@Composable
fun BottomSheetContent(onClose: () -> Unit, onContinue: () -> Unit) {
    var currentPage by remember { mutableStateOf<BottomSheetPage>(BottomSheetPage.PhoneVerification) }
    var phoneNumber by remember { mutableStateOf("") }

    when (currentPage) {
        is BottomSheetPage.PhoneVerification -> {
            PhoneVerificationPage(
                phoneNumber = phoneNumber,
                onPhoneNumberChange = { phoneNumber = it },
                onClose = onClose,
                onContinue = {
                    if (phoneNumber.length == 10) {
                        currentPage = BottomSheetPage.OtpVerification(phoneNumber)
                    }
                })
        }

        is BottomSheetPage.OtpVerification -> {
            OtpVerificationPage(
                phoneNumber = (currentPage as BottomSheetPage.OtpVerification).phoneNumber,
                onClose = onClose,
                onBack = { currentPage = BottomSheetPage.PhoneVerification },
                onVerify = onContinue
            )
        }
    }
}
