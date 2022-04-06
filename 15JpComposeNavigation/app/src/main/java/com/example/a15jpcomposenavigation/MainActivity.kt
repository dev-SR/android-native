package com.example.a15jpcomposenavigation

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder


import com.example.a15jpcomposenavigation.ui.theme.NavigationTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

const val ROOT_ROUTE = "root"
const val AUTH_ROUTE = "auth"
const val HOME_ROUTE = "home"

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login_screen")
    object Register : AuthScreen("register_screen")
}

sealed class HomeScreen(val route: String) {
    object List : HomeScreen("list_screen")
    object Details : HomeScreen("details_screen/{item_id}") {
        fun createRoute(id: Int) = "details_screen/$id"
    }
}

sealed class RootScreen(val route: String) {
    object Splash : RootScreen("splash_screen")
}

@Composable
fun MyApp() {
    BuildNavigation()
}

val lists = listOf("A", "B", "C", "D")

@OptIn(ExperimentalAnimationApi::class)
//import com.google.accompanist.navigation.animation.AnimatedNavHost
//import com.google.accompanist.navigation.animation.composable
//import com.google.accompanist.navigation.animation.navigation
//import com.google.accompanist.navigation.animation.rememberAnimatedNavController
@Composable
fun BuildNavigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = RootScreen.Splash.route,
//        route = ROOT_ROUTE
    ) {
        composable(
            route = RootScreen.Splash.route
        )
        {
            SplashScreen(navController = navController)
        }
        loginGraph(navController = navController)
        homeGraph(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(navController: NavController) {
    //home/list
    //home/registration
    navigation(startDestination = HomeScreen.List.route, route = HOME_ROUTE) {
        composable(
            route = HomeScreen.List.route,
            enterTransition = {
                when (initialState.destination.route) {
                    AuthScreen.Login.route, AuthScreen.Register.route -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                    HomeScreen.Details.route -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )

                    else -> null
                }
            }

        ) {
            ListScreen(navController = navController)
        }
        composable(
            route = HomeScreen.Details.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Up, animationSpec = tween(700)
                )
            }
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("item_id")
            DetailsScreen(navController = navController, id)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.loginGraph(navController: NavController) {
    //auth/login
    //auth/registration
    navigation(startDestination = AuthScreen.Login.route, route = AUTH_ROUTE) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AuthScreen.Register.route) {
            RegisterScreen(navController = navController)
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "LoginPage", style = MaterialTheme.typography.h2)
        Button(
            onClick = {
//                navController.navigate(route = Screen.List.route) {
//                    popUpTo(0)
//                }
                //or
//                navController.popBackStack()
//                navController.navigate(route = HOME_ROUTE)
                //Or
                navController.navigate(route = HOME_ROUTE) { popUpTo(0) }

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
                    navController.navigate(route = AuthScreen.Register.route) { popUpTo(0) }
                },
            )
        }
    }
}

@Composable
fun RegisterScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "RegisterPage", style = MaterialTheme.typography.h2)
        Button(onClick = {
            navController.navigate(route = HOME_ROUTE) {
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
                    navController.navigate(route = AuthScreen.Login.route) { popUpTo(0) }
                },
            )
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)        //  androidx.compose.animation.core
    }
    val systemUiController = rememberSystemUiController()
    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        // Customize the delay time
        delay(1000L)

        systemUiController.isStatusBarVisible = true //Show Status bar
        systemUiController.setStatusBarColor(Color.Black)

        navController.navigate(route = AuthScreen.Login.route) {
            popUpTo(0)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}

@Composable
fun ListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
    ) {
        Text(text = "List")
        Spacer(modifier = Modifier.size(5.dp))
        LazyColumn {
            items(lists) { i ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable {
                            val index = lists.indexOf(i)
                            navController.navigate(route = HomeScreen.Details.createRoute(index))
                        }, elevation = 4.dp
                ) {
                    Text(text = "$i", modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}

@Composable
fun DetailsScreen(navController: NavController, itemId: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        itemId?.let {
            val id = itemId.toInt()
            val item = lists[id]
            Text(text = "$item")
        }

        Spacer(modifier = Modifier.size(5.dp))
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Go to List")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationTheme {

    }
}