package com.example.a13jpcomposeui_i.ui_elements

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a13jpcomposeui_i.R

@Composable
fun CardExample() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card1()
            SpaceLine()
            Card2()
            SpaceLine()
            Card3()
        }
    }
}

@Composable
fun Card1() {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(
                onClick = {
                    Toast
                        .makeText(context, "Clicked", Toast.LENGTH_SHORT)
                        .show()
                }),
        backgroundColor = Color(0xFFE7F3FD),
        contentColor = Color.DarkGray,
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, Color.Black)

    ) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text("AB CDE", fontWeight = FontWeight.W700)
            Text("+0 12345678")
            Text("XYZ city.", color = Color.Gray)
        }
    }
}

@Composable
fun CreateMutableInteraction(): MutableInteractionSource = remember {
    MutableInteractionSource()
}

@Composable
fun CreateIndication(bounded: Boolean = true, color: Color = Color.Gray) = rememberRipple(
    bounded = bounded,
    color = color
)

@Composable
fun Card2() {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(
                interactionSource = CreateMutableInteraction(),
                indication = CreateIndication(),
                onClick = {
                    Toast
                        .makeText(context, "Clicked", Toast.LENGTH_SHORT)
                        .show()
                }),
        elevation = 8.dp
    ) {
        Text(text = "Card Custom Interaction", modifier = Modifier.padding(10.dp))
    }

}

@Composable
fun Card3() {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(
                interactionSource = CreateMutableInteraction(),
                indication = CreateIndication(),
                onClick = {
                    Toast
                        .makeText(context, "Clicked", Toast.LENGTH_SHORT)
                        .show()
                }),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Column {
                Text(
                    text = "Hello World",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Text(text = "Hello World.............")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    CardExample()
}