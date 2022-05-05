package com.example.a17jpcdaggerhilt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a17jpcdaggerhilt.ui.theme.DaggerHiltTheme
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor() {
    fun getUser(id: Long): User? {
        return getUsers().find { it.id == id }
    }

    fun getUsers(): List<User> {
        return listOf(
            User(id = 123, name = "James Bond", "jamesbond@007.com"),
            User(id = 345, name = "Batman", "batman@cave.com"),
            User(id = 999, name = "Arya Stark", "arya@winterfell.com")
        )
    }
}

data class User(
    val id: Long,
    val name: String,
    val email: String
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    private val _users = MutableStateFlow(userRepository.getUsers())
    val users: StateFlow<List<User>> = _users
}

@HiltViewModel
class UserDetailScreenViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun load(userId: Long) {
        _user.value = userRepository.getUser(id = userId)
    }
}

@Composable
fun UserDetailScreen(navController: NavController, vm: UserDetailScreenViewModel, userId: Long) {
    Column {
        vm.load(userId = userId)
        val user by vm.user.collectAsState()
        Column(Modifier.padding(all = 16.dp)) {
            Text(text = "Hello, I'm ${user?.name}")
            Text(text = "My email is ${user?.email}")
        }
    }

}

@Composable
fun HomeScreen(navController: NavController, vm: HomeScreenViewModel) {
    Column {
        val users by vm.users.collectAsState()
        users.forEach { user ->
            ClickableText(text = AnnotatedString(user.name), Modifier.padding(all = 16.dp),
                onClick = {
                    navController.navigate("users/${user.id}")
                })
        }
    }
}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // val mobile = Mobile(Battery("500mAh"), Processor("Snapdragon"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaggerHiltTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            val vm: HomeScreenViewModel by viewModels()
                            HomeScreen(navController, vm)
                        }
                        composable("users/{userId}") { backStackEntry ->
                            val vm: UserDetailScreenViewModel by viewModels()
                            UserDetailScreen(
                                navController,
                                vm,
                                (backStackEntry.arguments?.getString("userId", "") ?: "").toLong()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DaggerHiltTheme {
        App()
    }
}