package com.example.courseexpert.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courseexpert.R
import com.example.courseexpert.ui.theme.CourseExpertTheme

// this component is created from MainActivity.kt's CourseExpertApp Composable
@Composable
fun FAQScreen() {
    val backgroundImage: Painter = painterResource(id = R.drawable.background)
    val gloriaFontFamily = FontFamily(Font(R.font.gloria))
    val gloriaStyle = androidx.compose.ui.text.TextStyle(fontFamily = gloriaFontFamily, fontWeight = FontWeight.Normal)
    Image(
        painter = backgroundImage,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(" About Us / FAQs", fontSize = 40.sp, style = gloriaStyle)
            Text("")
            Text("About Course Expert:", fontSize = 25.sp)
            Text("")
            Text("Course Expert is an Android app designed to allow students to add reviews to classes they have taken. It helps other students pick out their courses by looking at the reviews of previous students.", fontSize = 20.sp)
            Text("")
            Text("How to Use:", fontSize = 25.sp)
            Text("")
            Text("Use the navigation bar below to switch pages. The FAQ button takes you to this page here! To search the reviews of a course, click 'Search'. To add your own review of a course, click 'Add Review'. TO view your profile and previous reviews you have made, click 'Profile'.", fontSize = 20.sp)
            Text("")
            Text("Contact Us:" , fontSize = 25.sp)
            Text("Email: courseexpert@gmail.com", fontSize = 20.sp)
            Text("Phone: 919-555-XXXX", fontSize = 20.sp)
        }
}