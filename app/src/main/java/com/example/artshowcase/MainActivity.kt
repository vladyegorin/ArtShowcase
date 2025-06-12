package com.example.artshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            ArtPicture(
                modifier = Modifier.align(Alignment.Center)
            )
        }



        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            ArtInfo()
            NextAndBackButtons(
                modifier = Modifier
                    .padding(bottom = 25.dp)
            )


        }
    }
}


@Composable
fun NextAndBackButtons(modifier: Modifier = Modifier){
    Row(){
        Button(onClick = {nextSlide()},
            modifier
                .width(120.dp)
                .padding(end = 8.dp)){
            Text("Next")
        }
        Button(onClick = {prevSlide()},
            modifier
                .width(120.dp)
                .padding(start = 8.dp)){
            Text("Previous")
        }
    }
}

@Composable
fun ArtInfo(slideNumber: Int = 1){
Text(text = "XDDDDDDDDD")
}

@Composable
fun ArtPicture(slideNumber: Int = 1, modifier: Modifier = Modifier){
Text(text = "picture")
}


fun nextSlide(){

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