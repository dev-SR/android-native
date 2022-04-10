package com.example.a15jpcnavigation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.BounceInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.currentBackStackEntryAsState


import com.example.a15jpcnavigation.ui.theme.NavigationTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    private val viewmodel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.apply {
            setKeepOnScreenCondition {
                viewmodel.loading.value
            }
//          animation for icon
//            setOnExitAnimationListener{ splashScreenView->
//                splashScreenView.iconView.apply {
//                    animate().apply {
//                        duration = 200
//                        scaleXBy(2f)
//                        scaleYBy(2f)
//                        withEndAction {
//                            splashScreenView.remove()
//                        }
//                        start()
//                    }
//                }
//            }
//            animating whole page on exit
            setOnExitAnimationListener { splashScreenProvider ->
                val splashScreenView = splashScreenProvider.view
                val anim = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    splashScreenView.height.toFloat()
                )
//                anim.interpolator = BounceInterpolator()
                anim.interpolator = AnticipateInterpolator()
                anim.duration = 500L
                anim.doOnEnd { splashScreenProvider.remove() }
                anim.start()
            }
        }
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

const val AUTH_ROUTE = "auth"
const val HOME_ROUTE = "home"

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login_screen")
    object Register : AuthScreen("register_screen")
}

sealed class HomeScreen(val route: String) {
    object List : HomeScreen("list_screen")
    object Profile : HomeScreen("profile")
    object Details : HomeScreen("details_screen/{item_id}") {
        fun createRoute(id: Int) = "details_screen/$id"
    }
}

sealed class BottomMenuData(
    val icon: ImageVector,
    val title: String,
    val route: String
) {
    object List : BottomMenuData(icon = Icons.Default.Home, "Home", "list_screen")
    object Profile : BottomMenuData(icon = Icons.Default.Person, "Profile", "profile")
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
        startDestination = AUTH_ROUTE,
//        route = ROOT_ROUTE
    ) {

        loginGraph(navController = navController)
        homeGraph(navController = navController)
    }
}

@Composable
fun BottomMenuBar(navController: NavController) {
    val items = listOf(
        BottomMenuData.List,
        BottomMenuData.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        modifier = Modifier.clip(
            RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        )
    ) {
        items.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val color = if (isSelected)
                MaterialTheme.colors.primary
            else
                MaterialTheme.colors.surface.copy(alpha = 0.5f)


            BottomNavigationItem(
                label = { Text(text = screen.title) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title,
                        tint = color
                    )
                },
                selected = isSelected,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        //popUpTo(navController.graph.findStartDestination().id) {
                        popUpTo(HomeScreen.List.route) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
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

        composable(route = HomeScreen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.loginGraph(
    navController: NavController
) {
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
    val systemUiController = rememberSystemUiController()
//    val color = MaterialTheme.colors.primary
    systemUiController.setStatusBarColor(Color.Black)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "LoginPage", style = MaterialTheme.typography.h2)
        Button(
            onClick = {
                navController.navigate(route = HOME_ROUTE) {
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
fun ProfileScreen(navController: NavController) {
    Scaffold(bottomBar = { BottomMenuBar(navController = navController) }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "ProfileScreen")
        }
    }
}

@Composable
fun ListScreen(navController: NavController) {


    Scaffold(bottomBar = { BottomMenuBar(navController = navController) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
        ) {
            Text(text = "List")

        }

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
        Text(text = "No need to have a bottom nav here..")
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
    NavigationTheme {}
}