package com.example.a13jpcomposeui_i.ui_elements

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a13jpcomposeui_i.ui.theme.ComposeTheme


@Composable
fun ButtonExample() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpaceLine()
            JustButton()
            SpaceLine()
            ColorButton()
            SpaceLine()
            IconTextButton()
            SpaceLine()
            IconButtons()
            SpaceLine()
            ButtonShape()
            SpaceLine()
            OutlinedButtonExample()
            SpaceLine()
            TextButtonExample()
            SpaceLine()
            DisableButton()
        }
    }
}

@Composable
fun DisableButton() {
    Button(
        onClick = {},
        enabled = false
    ) {
        Text("Add To Cart")
    }
}


@Composable
fun OutlinedButtonExample() {
    OutlinedButton(
        onClick = { /* Do something! */ },
        border = BorderStroke(
            ButtonDefaults.OutlinedBorderSize,
            color = Color.Green
        ),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)

    ) {
        Text("Outlined Button")
    }
}

@Composable
fun TextButtonExample() {
    TextButton(onClick = { /* Do something! */ }) {
        Text("I'm a Text Button")
    }
}

@Composable
fun SpaceLine() {
    Spacer(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.Black)
    )
}

@Composable
fun ButtonShape() {
    Button(
        onClick = {},
        shape = RoundedCornerShape(100.dp),
//        shape = CutCornerShape(10),
        elevation = null
    ) {
        Text(text = "Button")
    }
}

@Composable
fun IconButtons() {
    //    implementation "androidx.compose.material:material-icons-extended:$compose_version"
    Row {
        IconButton(
            onClick = { },
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description"
            )
        }

        IconButton(
            onClick = { },
        ) {
            Icon(
                Icons.Filled.Upload,
                contentDescription = "Localized description",
                tint = Color.Red
            )
        }

        IconButton(
            onClick = { },
        ) {
            Icon(
                Icons.Filled.Refresh,
                contentDescription = "Localized description",
                tint = Color.Blue
            )
        }

    }
}

@Composable
fun IconTextButton() {
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
        }) {
        Icon(
            Icons.Filled.Email,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text("Button")
    }

}

@Composable
fun ColorButton() {
    val context = LocalContext.current
    val isLight = MaterialTheme.colors.isLight
    Button(
//       Not colors = Color.Black,//Required: ButtonColors
        colors = if (isLight) buttonColors(Color.Black) else buttonColors(Color.White),
        onClick = {
            Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
        }) {
        Text("Button", color = if (isLight) Color.White else Color.Black)
    }
}

@Composable
fun JustButton() {
    val context = LocalContext.current
    Button(onClick = {
        Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
    }) {
        Text("Button")
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun ButtonPreview() {
    ComposeTheme {
        ButtonExample()
    }
}