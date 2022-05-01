package com.example.a13jpcomposeui_i.ui_elements

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
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
            SpaceLineCard()
            Card2()
            SpaceLineCard()
            Card3()
            SpaceLineCard()
            Card4()
            SpaceLineCard()
            WeatherUpdateCard()
            SpaceLineCard()
            AuthorUi()
        }
    }
}

@Composable
fun SpaceLineCard() {
    Spacer(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.Black)
    )
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
            Card(
                shape = CircleShape,
                border = BorderStroke(2.dp, color = Color(0xFF9FA8DA)),
                modifier = Modifier.size(48.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(48.dp),
                    contentDescription = "Profile picture holder"
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Column(
                modifier = Modifier
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
            ) {
                Text("Catalin Ghita", fontWeight = FontWeight.Bold)
                Text(
                    text = "Active now",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun Card4() {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Column(modifier = Modifier.padding(10.dp)) {
            Text("AB CDE", fontWeight = FontWeight.W700)
            Text("+0 12345678")
            Text("XYZ city", fontWeight = FontWeight.W300)
        }
    }
}

@Composable
fun WeatherUpdateCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.DarkGray
    ) {
        Box(Modifier.padding(10.dp)) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
                backgroundColor = Color.Black
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "20 minutes ago",
                        style = MaterialTheme.typography.caption,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "If you don't want to get wet today, don't forget your umbrella.",
                        style = MaterialTheme.typography.body1,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
            Image(
                painter = painterResource(id = R.drawable.cloudy),
                contentDescription = "weather overlap image",
                modifier = Modifier
                    .size(80.dp)
                    .align(alignment = Alignment.TopEnd)
                    .offset(x = (-60).dp)
            )
        }
    }
}

@Composable
fun AuthorUi() {
    Card(modifier = Modifier
        /* Card Style */
        .fillMaxWidth(0.8f)
        .height(250.dp)
        .padding(5.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier
            /* Card Will Contain One Column and Three Rows*/
            .fillMaxSize(),
            verticalArrangement = Arrangement.Top) {

            /* Row #1 --> Cover Image */
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .drawWithContent {
                    drawContent()
                    val strokeWidth = Stroke.DefaultMiter
                    val x = size.width
                    val y = size.height // - strokeWidth
                    drawLine(
                        brush = SolidColor(Color.Black),
                        strokeWidth = strokeWidth,
                        cap = StrokeCap.Square,
                        start = Offset.Zero.copy(y = y),
                        end = Offset(x = x, y = y)
                    )
                },
                horizontalArrangement = Arrangement.Start) {
                Image(painter = painterResource(id =  R.drawable.background), contentDescription = "Cover Image", contentScale = ContentScale.Crop)
            }

            /* Row #2 --> Author Image */
            Row(modifier = Modifier
                .fillMaxWidth(),
                //.offset(0.dp, (-32).dp),
                horizontalArrangement = Arrangement.Center) {
                Box(modifier = Modifier
                    .size(64.dp)
                    .offset(0.dp, (-32).dp)
                    .border(2.dp, Color.Black, shape = CircleShape)
                    .clip(CircleShape)){
                    Image(painter = painterResource(id =  R.drawable.profile), contentDescription = "Author Image", contentScale = ContentScale.Crop)
                }

            }

            /* Row #3 --> Title/Heading/Description */
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start)
            {
                Box(modifier = Modifier
                    .offset(0.dp, (-25).dp)
                    .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    .verticalScroll(rememberScrollState())
                ){
                    Text(text = "Main Cover Image\n" +
                            "Bottom Border on cover image\n" +
                            "Author image boxed, centered between cover image and  title\n" +
                            "Circular Border applied to cover image\n" +
                            "Title/Heading Box\n" +
                            "Vertical Scroll applied to Title box for long headings.\n" +
                            "Round Border radius applied to Card",
                        style = TextStyle(Color.Black, fontSize = 12.sp)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    CardExample()
}