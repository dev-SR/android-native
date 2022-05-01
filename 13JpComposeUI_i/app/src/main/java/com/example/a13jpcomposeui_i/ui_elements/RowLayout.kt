package com.example.a13jpcomposeui_i.ui_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LayoutExample() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Default", color = Color.Red)
            RowExample1()
            SpaceLineRow()
            Text("Weighted", color = Color.Red)
            RowExample2()
            SpaceLineRow()
            Text("x: horizontalArrangement (⇄)", color = Color.Red)
            RowExample3()
            SpaceLineRow()
            Text("x: horizontalArrangement (⇄) + y: verticalAlignment (⇵)", color = Color.Red)
            RowExample4()
            SpaceLineRow()
        }
    }
}

@Composable
fun SpaceLineRow() {
    Spacer(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.Black)
    )
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
    Surface(color = Color.LightGray) {
        Column {
            Text(
                text = "x start", style = TextStyle(
                    fontSize = 12.sp,
                )
            )
            ArrangementStart()
            Text(
                text = "x center", style = TextStyle(
                    fontSize = 12.sp,
                )
            )
            ArrangementCenter()
            Text(
                text = "x end", style = TextStyle(
                    fontSize = 12.sp,
                )
            )
            ArrangementEnd()
            Text(
                text = "x evenly", style = TextStyle(
                    fontSize = 12.sp,
                )
            )
            ArrangeEvenly()
            Text(
                text = "x around", style = TextStyle(
                    fontSize = 12.sp,
                )
            )
            ArrangeAround()
            Text(
                text = "x between", style = TextStyle(
                    fontSize = 12.sp,
                )
            )
            ArrangeBetween()
        }
    }
}

@Composable
fun ArrangementStart() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
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
fun ArrangementCenter() {
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
fun ArrangementEnd() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
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
fun ArrangeEvenly() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
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
fun ArrangeBetween() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
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
fun ArrangeAround() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
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
    Surface(color = Color.LightGray) {
        Column(Modifier.fillMaxSize()) {
            Text(
                text = "x center", style = TextStyle(
                    fontSize = 12.sp,
                )
            )
            RowOnlyX()
            Text(
                text = "x center + y center (Auto Center)", style = TextStyle(
                    fontSize = 12.sp,
                )
            )
            RowXY1()
            Text(
                text = "x center + y center (Top)", style = TextStyle(
                    fontSize = 12.sp,
                )
            )
            RowXY2()

        }
    }
}

@Composable
fun RowOnlyX() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    Row(
        modifier = Modifier
            .size(width = screenWidth.dp, height = 100.dp)
            .background(
                Color(
                    0xFFDDC8F7
                )
            ),
        horizontalArrangement = Arrangement.Center,

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
                .size(20.dp)
                .background(Color.Red)
        )

    }

}


@Composable
fun RowXY1() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    Row(
        modifier = Modifier
            .size(width = screenWidth.dp, height = 100.dp)
            .background(
                Color(
                    0xFFDDC8F7
                )
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
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
                .size(25.dp)
                .background(Color.Red)
        )
    }

}

@Composable
fun RowXY2() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    Row(
        modifier = Modifier
            .size(width = screenWidth.dp, height = 100.dp)
            .background(
                Color(
                    0xFFDDC8F7
                )
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.Top) {
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
                    .size(20.dp)
                    .background(Color.Red)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultLayoutPreview() {
    LayoutExample()
}