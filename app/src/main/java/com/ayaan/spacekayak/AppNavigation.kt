package com.ayaan.spacekayak

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController,modifier: Modifier) {
    NavHost(navController = navController, startDestination = Route.Onboarding.path){
        composable(Route.Onboarding.path) {
            OnboardingScreen(navController,modifier)
        }
        composable(Route.Login.path) {  }
        composable(Route.TC.path) {  }
        composable(Route.PhoneNumber.path) {  }
        composable(Route.Otp.path) {  }
    }
}
sealed class Route(val path: String) {
    object Onboarding : Route("onboarding")
    object Login : Route("login")
    object TC : Route("tc")
    object PhoneNumber: Route("phone_number")
    object Otp: Route("otp")
}