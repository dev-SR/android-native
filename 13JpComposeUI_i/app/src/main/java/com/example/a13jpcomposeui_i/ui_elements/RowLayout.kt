package com.example.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a13jpcomposeui_i.ui_elements.SpaceLine

@Composable
fun LayoutExample() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            RowExample1()
            SpaceLine()
            RowExample2()
            SpaceLine()
            RowExample3()
            SpaceLine()
            RowExample4()
            SpaceLine()

        }
    }
}

@Composable
fun RowExample1() {
    Row {
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
fun RowExample2() {
    Row {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(Color.Blue)
        )
        Box(
            modifier = Modifier
                .height(30.dp)
                .weight(1f)
                .background(Color.Green)
        )
        Box(
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
                .background(Color.Red)
        )

    }
}

@Composable
fun RowExample3() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
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
}

@Composable
fun RowExample4() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(Color.Blue)
        )
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Green)
        )
        Box(
            modifier = Modifier
                .width(20.dp)
                .fillMaxSize()
                .background(Color.Red)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultLayoutPreview() {
    LayoutExample()
}