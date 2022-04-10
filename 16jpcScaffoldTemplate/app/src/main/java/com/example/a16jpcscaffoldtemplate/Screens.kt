package com.example.a16jpcscaffoldtemplate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

const val AUTH_ROUTE = "auth"
const val MAIN_ROUTE = "main"

sealed class Screens(val route: String, val title: String) {
    sealed class AuthScreen(
        route: String,
        title: String
    ) : Screens(
        route,
        title
    ) {
        object Login : AuthScreen("/login_screen", "login_screen")
        object Register : AuthScreen("/register_screen", "register_screen")
    }

    sealed class HomeScreens(
        route: String,
        title: String,
        val icon: ImageVector
    ) : Screens(
        route,
        title
    ) {
        object Home : HomeScreens("/home", "Home", Icons.Filled.Home)
        object Favorite : HomeScreens("/favorite", "Favorite", Icons.Filled.Favorite)
        object Notification :
            HomeScreens("/notification", "Notification", Icons.Filled.Notifications)
    }

    sealed class DrawerScreens(
        route: String,
        title: String
    ) : Screens(route, title) {
        object Account : DrawerScreens("account", "Account")
        object Help : DrawerScreens("help", "Help")
    }
}

val screensInHomeFromBottomNav = listOf(
    Screens.HomeScreens.Home,
    Screens.HomeScreens.Favorite,
    Screens.HomeScreens.Notification,
)

val screensFromDrawer = listOf(
    Screens.DrawerScreens.Account,
    Screens.DrawerScreens.Help,
)

@Composable
fun LoginScreen(navController: NavController, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.AuthScreen.Login)
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Black)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "LoginPage", style = MaterialTheme.typography.h2)
        Button(
            onClick = {
                navController.navigate(route = MAIN_ROUTE) {
                    popUpTo(0)
                }
                //or
//                navController.popBackStack()
//                navController.navigate(route = HOME_ROUTE)
            }) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row {
            Text(text = "Not registered yet? ", style = MaterialTheme.typography.subtitle1)
            Text(
                text = "Create New Account",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    navController.navigate(route = Screens.AuthScreen.Register.route)
                },
            )
        }
    }
}

@Composable
fun RegisterScreen(navController: NavController, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.AuthScreen.Register)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "RegisterPage", style = MaterialTheme.typography.h2)
        Button(onClick = {
            navController.navigate(route = MAIN_ROUTE) {
                popUpTo(0)
            }
        }) {
            Text(text = "Register")
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row {
            Text(text = "Already have an Account? ", style = MaterialTheme.typography.subtitle1)
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    navController.navigate(route = Screens.AuthScreen.Login.route)
                },
            )
        }
    }
}

@Composable
fun Home(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val systemUiController = rememberSystemUiController()
    val  color = MaterialTheme.colors.primaryVariant
    systemUiController.setStatusBarColor(color = color)
    viewModel.setCurrentScreen(Screens.HomeScreens.Home)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home.", style = MaterialTheme.typography.h4)
    }
}


@Composable
fun Favorite(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.HomeScreens.Favorite)
    val clickCount by viewModel.clickCount.collectAsState()
    var click = clickCount ?: 0
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Favorite.", style = MaterialTheme.typography.h4)
        Button(
            onClick = {
                click++
                viewModel.updateClick(click)
            }
        ) {
            Text("clicked: $click")
        }
    }
}

@Composable
fun Notification(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.HomeScreens.Notification)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Notification.", style = MaterialTheme.typography.h4)
    }
}


@Composable
fun Account(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.DrawerScreens.Account)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Account.", style = MaterialTheme.typography.h4)
    }
}

@Composable
fun Help(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.DrawerScreens.Help)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Help.", style = MaterialTheme.typography.h4)
    }
}