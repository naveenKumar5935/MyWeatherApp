package com.example.myweatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.R
import com.example.myweatherapp.WeatherType
import com.example.myweatherapp.ui.theme.DarkBlue
import com.example.myweatherapp.ui.theme.PoppinsFontFamily

@Composable
fun WeatherCard(weatherType: WeatherType) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(10.dp),
        colors = CardDefaults.cardColors(DarkBlue)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Today 15:00",
                color = Color.White,
                textAlign = TextAlign.Right,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

            )

            Image(painter = painterResource(id = weatherType.iconRes),
                contentDescription = "",
                alignment = Alignment.Center,
                modifier = Modifier.size(200.dp)
            )

            Text(text = weatherType.weatherDesc,
                color = Color.White,
                fontSize = 40.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(text = "16 C",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {

                Humidity(R.drawable.ic_pressure, "1014hpa")
                Humidity(R.drawable.ic_drop, "30%")
                Humidity(R.drawable.ic_wind,"14km/h")
            }

        }
    }
}

@Composable
fun Humidity(icon:Int, text:String) {
    Row {
        Icon(painter = painterResource(id = icon),
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier.size(30.dp))
        Text(text = text, modifier = Modifier.offset(x=4.dp),
            color = Color.White,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Light)
    }
}