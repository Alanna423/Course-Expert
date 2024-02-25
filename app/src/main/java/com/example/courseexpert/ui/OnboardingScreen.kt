package com.example.courseexpert.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courseexpert.R
import com.example.courseexpert.ui.theme.CourseExpertTheme
import java.time.format.TextStyle

//@Composable
//fun CourseExpertApp() {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Text("Life do be like that.")
//    }
//}

// this component is created from MainActivity.kt's CourseExpertApp Composable
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun OnboardingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Load your image from resources
        val backgroundImage: Painter = painterResource(id = R.drawable.background)
        val gloriaFontFamily = FontFamily(Font(R.font.gloria))
        val gloriaStyle = androidx.compose.ui.text.TextStyle(fontFamily = gloriaFontFamily, fontWeight = FontWeight.Normal)

//        val courseExpert: Painter = painterResource(id = R.drawable.expert)
//        Image(
//            painter = courseExpert,
//            contentDescription = null,
//            modifier = Modifier.size( 50.dp),
//            contentScale = ContentScale.FillBounds
//        )
        // Set the image as the background
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Text("Course Expert", fontSize = 50.sp, modifier = Modifier.align(Alignment.TopCenter).padding(top = 150.dp), style = gloriaStyle)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 300.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        "Login",
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
//                    style = customFontFamily
                    )
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 25.dp, end = 10.dp)) {
                    Text(
                        "Create Account",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 10.dp, start = 5.dp, end = 5.dp)
            ) {
                Text(
                    "Please fill out email and password to create an account",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }

        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 450.dp)
        ) {
            Text(
                "Email", modifier = Modifier.padding(start = 25.dp), fontSize = 25.sp
            )
            var txt by remember { mutableStateOf(TextFieldValue()) }
            TextField(
                value = txt,
                onValueChange = { txt = it },
                label = { ("hi") },
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                "Password", modifier = Modifier.padding(start = 25.dp), fontSize = 25.sp
            )
            var txt2 by remember { mutableStateOf(TextFieldValue()) }
            TextField(
                value = txt2,
                onValueChange = { txt2 = it },
                label = { ("hi") },
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                "Re-enter Password", modifier = Modifier.padding(start = 25.dp), fontSize = 25.sp
            )
            var txt3 by remember { mutableStateOf(TextFieldValue()) }
            TextField(
                value = txt3,
                onValueChange = { txt3 = it },
                label = { ("hi") },
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {
            Text("Create Account", fontSize = 25.sp)
        }
    }
    }
//@Composable