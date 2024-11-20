package com.example.dynamicimageviewerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.foundation.border
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicImageViewerApp()
        }
    }
}

@Composable
fun DynamicImageViewerApp() {
    // State variables to store the current image and its description
    var currentImage by remember { mutableIntStateOf(R.drawable.honeybun1) }
    var currentImageDescription by remember {
        mutableStateOf("HoneyBun with a Green Ball")
    }
    // Define colors used throughout the UI
    val greenButtonColor = Color(0xFF4CAF50)
    val tanButtonColor = Color(0xFFD2B48C)
    val lightBlueButtonColor = Color(0xFF87CEEB)
    val lightTanBackgroundColor = Color(0xFFFAF0E6)

    // Main container for the app with a background color
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightTanBackgroundColor)
    ){
        // Center-aligned column for the content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animated content to display the image with fade-in/out transition
            AnimatedContent(targetState = currentImage,
                transitionSpec = {
                    fadeIn(animationSpec = tween(300)) togetherWith fadeOut(animationSpec = tween(300))
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                label = "ImageTransition"
            ){ targetState: Int ->
                Image(
                    painter = painterResource(id = targetState),
                    contentDescription = currentImageDescription,
                    modifier = Modifier
                        .width(200.dp)
                        .border(4.dp, tanButtonColor)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Description of the currently displayed image
            Text(
                text = currentImageDescription,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Buttons for changing the image
            Button(
                onClick = {
                    currentImage = R.drawable.honeybun1
                    currentImageDescription = "HoneyBun with a green ball"
                },
                modifier = Modifier.fillMaxWidth(0.5f) .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = greenButtonColor
                )
            ) {
                Icon (
                    painter= painterResource(id = R.drawable.ic_ball),
                    contentDescription = "Ball",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Green")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    currentImage = R.drawable.honeybun2
                    currentImageDescription = "HoneyBun laying on a tan couch"
                },
                modifier = Modifier.fillMaxWidth(0.5f) .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = tanButtonColor
                )
            ) {
                Icon (
                    painter= painterResource(id = R.drawable.ic_couch),
                    contentDescription = "Ball",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Tan")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    currentImage = R.drawable.honeybun3
                    currentImageDescription = "Close up of HoneyBun's face with blue eyes"
                },
                modifier = Modifier.fillMaxWidth(0.5f) .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightBlueButtonColor
                )
            ) {
                Icon (
                    painter= painterResource(id = R.drawable.ic_dog_face),
                    contentDescription = "Ball",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Blue")
            }
        }
    }
}