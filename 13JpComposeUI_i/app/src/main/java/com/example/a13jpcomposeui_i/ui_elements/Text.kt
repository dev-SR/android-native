package com.example.a13jpcomposeui_i.ui_elements

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a13jpcomposeui_i.ui.theme.ComposeTheme

@Preview(name = "Light Mode", showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        TextExample()
    }
}


@Composable
fun TextExample() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.fillMaxWidth()) {
            JustText()
            TextParameter(name = "Soikat")
            StyleText()
            FontText()
            TextOverflowEx()
            TextAlignment()
            TextWithClick()
            TextSpan()
            TextSpanClick()
            TextSelection()
        }
    }
}


@Composable
fun JustText() {
    Text(text = "Hello World")
}

@Composable
fun TextParameter(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun StyleText() {
    Text(text = "Hello World", style = MaterialTheme.typography.h5)
}

@Composable
fun FontText() {
    Text(
        text = "Hello World",
//        style = TextStyle(
//            fontFamily = FontFamily.Monospace,
//            fontWeight = FontWeight.Bold,
//            fontSize = 20.sp,
//            letterSpacing = 5.sp
//        )
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.W800,
        fontSize = 20.sp,
        letterSpacing = 5.sp
    )
}

@Composable
fun TextOverflowEx() {
    Text(
        text = "Hello World,Hello World, Hello World, Hello World, Hello World, Hello World,  Hello World,   ",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.body2
    )
}

@Composable
fun TextAlignment() {
    Surface(color = Color.LightGray) {
        Column() {

            Text(
                text = "Hello World",
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
                text = "Hello World",
                color = Color.Blue,
                modifier = Modifier.width(300.dp),
                textAlign = TextAlign.End
            )
        }
    }

}

@Composable
fun TextWithClick() {
    val context = LocalContext.current
    Surface(color = Color.Magenta) {
        Text(text = "Click Me",
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    Toast
                        .makeText(context, "Clicked...", Toast.LENGTH_SHORT)
                        .show()
                })
    }

}

@Composable
fun TextSpan() {
    Text(buildAnnotatedString {
        append("Hello ")
        withStyle(
            style = SpanStyle(
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        ) {
            append("World!!")
        }
    })
}

@Composable
fun TextSpanClick() {
    val context = LocalContext.current

    val span = buildAnnotatedString {
        append("Hello ")
        pushStringAnnotation(tag = "click", annotation = "annotation")
        withStyle(
            style = SpanStyle(
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        ) {
            append("World!!")
        }
        pop()
    }
    ClickableText(text = span, onClick = { offset ->
        span.getStringAnnotations(tag = "click", start = offset, end = offset).firstOrNull()
            ?.let { annotation ->
                Toast
                    .makeText(context, annotation.item, Toast.LENGTH_SHORT)
                    .show()
            }
    })

}

@Composable
fun TextSelection() {
    SelectionContainer {
        Text(text = "Hello World", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    }
}


