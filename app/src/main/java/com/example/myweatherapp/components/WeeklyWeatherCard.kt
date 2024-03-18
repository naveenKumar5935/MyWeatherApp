package com.example.myweatherapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.R
import com.example.myweatherapp.WeatherType
import com.example.myweatherapp.data.Hour


@Composable
fun WeeklyWeatherCard(hourList: List<Hour>) {


    Column {
        Text(text = "Today",
            fontWeight = FontWeight.Light,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(15.dp,10.dp,0.dp,0.dp))

        LazyRow(modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,content = {
            item {
                  hourList.forEach{hour ->
                      val weathertype =WeatherType.fromWMO(hour.condition.code)
                abc(time = hour.time.split(" ")[1], icon = weathertype.iconRes , temperature = hour.temp_c.toString())
            }
            }
        })


    }

}

@Composable
fun abc(time:String,icon:Int,temperature:String, tint:Color = Color.White) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(text = time, color = Color.White, fontWeight = FontWeight.Light)
        Icon(painter = painterResource(id = icon),
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .size(30.dp)
                .offset(y = 4.dp))
        Text(text = temperature, color = Color.White, fontWeight = FontWeight.Light,
            modifier = Modifier.offset(y=4.dp))
    }
}