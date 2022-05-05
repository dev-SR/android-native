package com.example.a17jpcdaggerhilt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a17jpcdaggerhilt.ui.theme.DaggerHiltTheme
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier

class Battery @Inject constructor(
    @Named("battery")
    val capacity: String
) {}

class Processor @Inject constructor(
    @Named("processor")
    val company: String
) {}

@Module
@InstallIn(SingletonComponent::class)
class StringModule {
    @Provides
    @Named("battery")
    fun getBatteries() = "5000mAh"

    @Provides
    @Named("processor")
    fun getProcessor() = "5000mAh"
}

class Mobile @Inject constructor(
    var battery: Battery,
    var processor: Processor
) {
    fun print() {
        Log.d("inject", "Mobile Created")
    }
}





@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var mobile: Mobile

    // val mobile = Mobile(Battery("500mAh"), Processor("Snapdragon"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mobile.print()
        setContent {
            DaggerHiltTheme {
                App()
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