package com.example.a13jpcomposeui_i.ui_elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a13jpcomposeui_i.R


@Composable
fun ImageResource() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column() {
            Image1()
        }
    }

}

@Composable
fun Image1() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
        , content = {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape).size(100.dp),
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ImagePreview() {
    ImageResource()
}