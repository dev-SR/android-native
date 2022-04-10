package com.example.a16jpcscaffoldtemplate

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.a16jpcscaffoldtemplate.ui.theme.ScaffoldTemplateTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.apply {
            setKeepOnScreenCondition {
                viewModel.loading.value
            }
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
            ScaffoldTemplateTheme {
                AppScaffold(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppScaffold(viewModel: MainViewModel) {

    val navController = rememberAnimatedNavController()
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val currentScreen by viewModel.currentScreen.collectAsState()
    Log.d("route", currentScreen.toString())
    var topBar: @Composable () -> Unit = {
        if (currentScreen != Screens.AuthScreen.Login && currentScreen != Screens.AuthScreen.Register) {
            TopBar(
                title = currentScreen!!.title,
                buttonIcon = Icons.Filled.Menu,
                onButtonClicked = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        }
        if (currentScreen == Screens.DrawerScreens.Help || currentScreen == Screens.DrawerScreens.Account) {
//            topBar = {
            TopBar(
                title = Screens.DrawerScreens.Help.title,
                buttonIcon = Icons.Filled.ArrowBack,
                onButtonClicked = {
                    navController.popBackStack()
                }
            )
//            }
        }
    }


    val bottomBar: @Composable () -> Unit = {
        when (currentScreen) {
            Screens.HomeScreens.Home, Screens.HomeScreens.Favorite, Screens.HomeScreens.Notification -> {
                BottomBar(
                    navController = navController,
                )
            }
            else -> null
        }
//        if (currentScreen != Screens.AuthScreen.Login && currentScreen != Screens.AuthScreen.Register) {
//
//        }
    }

    Scaffold(
        topBar = {
            topBar()
        },
        bottomBar = {
            bottomBar()
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            Drawer { route ->
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                navController.navigate(route) {
                    popUpTo(Screens.HomeScreens.Home.route) {
//                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
    ) {
        BuildNavigation(viewModel = viewModel, navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BuildNavigation(viewModel: MainViewModel, navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AUTH_ROUTE,
    ) {
        authGraph(navController = navController, viewModel = viewModel)
        homeGraph(navController = navController, viewModel = viewModel)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authGraph(navController: NavHostController, viewModel: MainViewModel) {
    navigation(startDestination = Screens.AuthScreen.Login.route, route = AUTH_ROUTE) {
        composable(Screens.AuthScreen.Login.route) {
            LoginScreen(
                navController,
                viewModel
            )
        }
        composable(Screens.AuthScreen.Register.route) { RegisterScreen(navController, viewModel) }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(navController: NavHostController, viewModel: MainViewModel) {
    navigation(startDestination = Screens.HomeScreens.Home.route, route = MAIN_ROUTE) {
        //Passing viewModel -> For Managing TopAPP and Drawer
        composable(Screens.HomeScreens.Home.route) { Home(viewModel = viewModel) }
        composable(Screens.HomeScreens.Favorite.route) { Favorite(viewModel = viewModel) }
        composable(Screens.HomeScreens.Notification.route) { Notification(viewModel = viewModel) }
        composable(Screens.DrawerScreens.Account.route) { Account(viewModel = viewModel) }
        composable(Screens.DrawerScreens.Help.route) { Help(viewModel = viewModel) }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldTemplateTheme {
    }
}