package com.example.graduationproject

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ui.add_offer.addOfferRoute
import com.example.ui.login.loginRoute
import com.example.ui.confirm_number.otpRoute
import com.example.ui.home.homeRoute
import com.example.ui.post_details.postDetailsRoute
import com.example.ui.signup.signupRoute
import com.example.ui.topic_see_all.topicSeeAllRoute


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "signup") {
        signupRoute(navController)
        loginRoute(navController)
        otpRoute(navController)
        homeRoute(navController)
        topicSeeAllRoute(navController)
        postDetailsRoute(navController)
        addOfferRoute(navController)
    }
}
