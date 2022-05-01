package com.example.a13jpcomposeui_i.ui_elements


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColumnLayoutExample() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Row {
            ColumnExample1()
            SpaceLineCol()
            ColumnExample2()
            SpaceLineCol()
            ColumnExample3()
            SpaceLineCol()
            // Y: verticalArrangement (⇵)
            ColumnExample4()
            SpaceLineCol()
            ColumnExample5()

        }
    }
}

@Composable
fun SpaceLineCol() {
    val isLight = MaterialTheme.colors.isLight
    val color = if (isLight) Color.Black else Color.White
    Spacer(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .width(1.dp)
            .fillMaxHeight()
            .background(color)
    )
}


@Composable
fun ColumnExample1() {
    Column {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(Color.Blue)
        )
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(Color.Green)
        )
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(Color.Red)
        )
    }

}

@Composable
fun ColumnExample2() {
    Column {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(Color.Blue)
        )
        Box(
            modifier = Modifier
                .width(30.dp)
                .weight(1f)
                .background(Color.Green)
        )
        Box(
            modifier = Modifier
                .width(40.dp)
                .weight(1f)
                .background(Color.Red)
        )

    }
}


@Composable
fun ColumnExample3() {
    Box {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp, height = 300.dp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .size(30.dp, height = 500.dp)
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .size(30.dp, height = 600.dp)
                    .fillMaxSize()
                    .background(Color.Red)
            )
        }
        Text(
            modifier = Modifier
                .rotate(-90f)
                .align(Alignment.Center),
            text = "scrollable"
        )
    }
}

@Composable
fun ColumnExample4() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    Box(
        modifier = Modifier.size(width = 80.dp, height = screenHeight.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .rotate(-90f),
            text = "Y (⇄)"
        )
        Column(
            modifier = Modifier.size(width = 80.dp, height = screenHeight.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.Blue)
            )

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.Red)
            )

        }
    }
}

@Composable
fun ColumnExample5() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.Green)
            )


            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.Red)
            )
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = " X(⇄) | Y(⇵)",
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultColumnLayoutPreview() {
    ColumnLayoutExample()
}