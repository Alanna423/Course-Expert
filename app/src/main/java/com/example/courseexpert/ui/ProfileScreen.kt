package com.example.courseexpert.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.courseexpert.R
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment

// this component is created from MainActivity.kt's CourseExpertApp Composable
@Composable
fun ProfileScreen() {
    val backgroundImage: Painter = painterResource(id = R.drawable.background)
    val gloriaFontFamily = FontFamily(Font(R.font.gloria))
    val gloriaStyle = androidx.compose.ui.text.TextStyle(fontFamily = gloriaFontFamily, fontWeight = FontWeight.Normal)
    val profileImage : Painter = painterResource(id = R.drawable.baseline_account_circle_24)
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
    )
    {
        Text("   Your Account", fontSize = 40.sp, style = gloriaStyle)
        Image(
            painter = profileImage,
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillBounds
        )
        Text("Email:", fontSize = 30.sp)
        Text("ebenn@unc.edu", fontSize = 30.sp)
        Text("")
        Text("Password:", fontSize = 30.sp)
        Text("***********", fontSize = 30.sp)



    }
}

@Composable
fun Image2(painter: Painter, contentDescription: Nothing?, contentScale: ContentScale) {

}

@Composable
fun <ImageVector> SomeComposable(icon: ImageVector) {

}
