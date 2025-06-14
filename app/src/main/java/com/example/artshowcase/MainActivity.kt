package com.example.artshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artshowcase.ui.theme.ArtShowcaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtShowcaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ArtShowcaseLayout()
                }
            }
        }
    }
}

@Composable
fun ArtShowcaseLayout(){
    //do a big column, with going top to bottom: image, info, buttons.
    var slideNumber = remember { mutableStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.weight(4f),
            contentAlignment = Alignment.Center,

        ) {
            ArtPicture(
                slideNumber.value,
                modifier = Modifier.align(Alignment.Center)
            )
        }



        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            ArtInfo(
                slideNumber.value
                )
            NextAndBackButtons(
                slideNumber = slideNumber.value,
                onSlideChange = { slideNumber.value = it},
                modifier = Modifier
                    .padding(bottom = 25.dp)
            )


        }
    }
}


@Composable
fun NextAndBackButtons(slideNumber: Int,onSlideChange: (Int) -> Unit, modifier: Modifier = Modifier){
    Row{

        Button(onClick = {onSlideChange(prevSlide(slideNumber))},
            modifier
                .width(120.dp)
                .padding(end = 8.dp)){
            Text("Previous")
        }
        Button(onClick = {onSlideChange(nextSlide(slideNumber))},
            modifier
                .width(120.dp)
                .padding(start = 8.dp)){
            Text("Next")
        }
    }
}

@Composable
fun ArtInfo(slideNumber: Int,modifier: Modifier = Modifier){
    var paintName = "NAME"
    var paintInfo = "INFO"
    if(slideNumber==1){
        paintName = stringResource(R.string.picture_1_name)
        paintInfo = stringResource(R.string.picture_1_desc)
    }
    else if(slideNumber==2){
        paintName = stringResource(R.string.picture_2_name)
        paintInfo = stringResource(R.string.picture_2_desc)
    }
    else{
        paintName = stringResource(R.string.picture_3_name)
        paintInfo = stringResource(R.string.picture_3_desc)
    }
    Surface(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
            .padding(25.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        color = Color.LightGray
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = paintName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = paintInfo,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ArtPicture(slideNumber: Int, modifier: Modifier = Modifier){
    val imageResource = when(slideNumber){
        1->R.drawable.picture_1
        2->R.drawable.picture_2
        else->R.drawable.picture_3
    }
    Box(
        modifier = Modifier
            .border(2.dp,Color.Gray, shape = RectangleShape)
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = modifier
                .size(350.dp),
            contentScale = ContentScale.FillBounds // Stretches to fill bounds
        )
    }
}


fun nextSlide(slideNumber: Int = 1): Int{
    //do logic like in lemonade to increase counter
    var newSlideNumber = slideNumber
    if(slideNumber<3){
        newSlideNumber++
    }
    return newSlideNumber
}

fun prevSlide(slideNumber: Int = 1): Int{
    var newSlideNumber = slideNumber
    if(slideNumber>1){
        newSlideNumber--
    }
    return newSlideNumber
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtShowcaseTheme {
        ArtShowcaseLayout()
    }
}