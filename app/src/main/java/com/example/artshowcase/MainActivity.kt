package com.example.artshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var slideNumber: Int = 1
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
                slideNumber,
                modifier = Modifier.align(Alignment.Center)
            )
        }



        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            ArtInfo(
                slideNumber
                )
            NextAndBackButtons(
                modifier = Modifier
                    .padding(bottom = 25.dp)
            )


        }
    }
}


@Composable
fun NextAndBackButtons(modifier: Modifier = Modifier){
    Row{

        Button(onClick = {prevSlide()},
            modifier
                .width(120.dp)
                .padding(end = 8.dp)){
            Text("Previous")
        }
        Button(onClick = {nextSlide()},
            modifier
                .width(120.dp)
                .padding(start = 8.dp)){
            Text("Next")
        }
    }
}

@Composable
fun ArtInfo(slideNumber: Int = 1,modifier: Modifier = Modifier){
    var paintName = "NAME"
    var paintInfo = "INFO"
    if(slideNumber==1){
        paintName = stringResource(R.string.picture_1_name)
        paintInfo = stringResource(R.string.picture_1_desc)
    }
    Surface(
        modifier
            .wrapContentSize()
            .padding(25.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        color = Color.LightGray,

    ) {
        Column(modifier
            .padding(16.dp)) {
            Text(paintName)
            Text(paintInfo)
        }
    }
}

@Composable
fun ArtPicture(slideNumber: Int = 1, modifier: Modifier = Modifier){
    val imageResource = when(slideNumber){
        1->R.drawable.picture_1
        2->R.drawable.picture_2
        else->R.drawable.picture_3
    }
    Image(
        painter = painterResource(imageResource),
        contentDescription = null,
        modifier = modifier
            .size(350.dp), // Fixed size for all images
        contentScale = ContentScale.FillBounds // Stretches to fill bounds
    )

}


fun nextSlide(){
    //do logic like in lemonade to increase counter
}

fun prevSlide(){

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtShowcaseTheme {
        ArtShowcaseLayout()
    }
}