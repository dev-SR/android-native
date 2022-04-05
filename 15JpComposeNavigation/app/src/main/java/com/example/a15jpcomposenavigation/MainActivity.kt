package com.example.a15jpcomposenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a15jpcomposenavigation.ui.theme.NavigationTheme


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

@Composable
fun MyApp() {
    BuildNavigation()
}

val lists = listOf("A", "B", "C", "D")

@Composable
fun BuildNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "/list_screen") {
        composable(route = "/list_screen") {
            ListScreen(navController = navController)
        }
        composable(route = "/details_screen") {
            DetailsScreen(navController = navController)
        }
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
                            navController.navigate("/details_screen")

                        }, elevation = 4.dp
                ) {
                    Text(text = "$i", modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}

@Composable
fun DetailsScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "DetailsScreen")
        Spacer(modifier = Modifier.size(5.dp))
        Button(onClick = {
//            navController.navigate("/list_screen")
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