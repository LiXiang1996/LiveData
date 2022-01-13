package com.example.mycomposefirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.mycomposefirst.ui.theme.MyComposeFirstTheme


class MainActivityConstrainLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeFirstTheme {
                Column() {
                    ConstraintLayoutContent()
                    TwoTexts(text1 = "日你妈", text2 = "危机吧")


                    LazyColumn {
                        // Add a single item
                        item {
                            Text(text = "First item")
                        }

                        // Add 5 items
                        items(5) { index ->
                            Text(text = "Item: $index")
                        }

                        // Add another single item
                        item {
                            Text(text = "Last item")
                        }
                    }


                }

            }
        }
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout() {
        val (button1, button2, text) = createRefs()
        Button(onClick = { }, modifier = Modifier.constrainAs(button1) {
            top.linkTo(parent.top, margin = 16.dp)
        }) {
            Text(text = "按钮1")
        }

        val guideline = createGuidelineFromStart(fraction = 0.5f)

        Text(text = "芽儿总", modifier = Modifier
            .padding(8.dp)
            .constrainAs(text) {
                top.linkTo(button1.bottom, margin = 1.dp)
                centerHorizontallyTo(parent) //水平居中
                //                linkTo(start = guideline,end = parent.end)
                width = Dimension.preferredWrapContent
            })
        val barrier = createEndBarrier(button1, text, margin = 1.dp)

        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(button2) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(barrier)
        }) {
            Text(text = "按钮2")
        }
    }
}


@Preview
@Composable
fun AConstraintLayoutContent() {
    MyComposeFirstTheme {
        ConstraintLayoutContent()
    }
}

fun decoupleConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button3 = createRefFor("button3")
        val text2 = createRefFor("text2")
        constrain(button3) {
            bottom.linkTo(parent.bottom, margin = 18.dp)
        }
        constrain(text2) {
            bottom.linkTo(button3.top, margin = 29.dp)
        }
    }
}


@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(modifier = Modifier
            .weight(1f)
            .padding(start = 4.dp)
            .wrapContentWidth(Alignment.Start), text = text1)

        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Text(modifier = Modifier
            .weight(1f)
            .padding(end = 4.dp)
            .wrapContentWidth(Alignment.End),

            text = text2)
    }
}

@Preview
@Composable
fun TwoTextsPreview() {
    MyComposeFirstTheme() {
        Surface {
            TwoTexts(text1 = "Hi", text2 = "there")
        }
    }
}
