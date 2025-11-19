package com.ayaan.spacekayak.screens.components.componentnavigation

sealed class BottomSheetPage {
    object PhoneVerification : BottomSheetPage()
    data class OtpVerification(val phoneNumber: String) : BottomSheetPage()
}