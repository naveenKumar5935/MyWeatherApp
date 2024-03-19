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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.R
import com.example.myweatherapp.data.Weather
import com.example.myweatherapp.ui.theme.DarkBlue
import com.example.myweatherapp.ui.theme.PoppinsFontFamily



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherCard(weather: Weather, cityEntered: String, cityEnteredChange: (city:String) -> Unit) {

    var alertDialogShow by remember {
        mutableStateOf(false)
    }
    var text by remember {
        mutableStateOf("")
    }


    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(10.dp),
        colors = CardDefaults.cardColors(DarkBlue)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                IconButton(onClick = { alertDialogShow=true }) {
                    Icon(imageVector = Icons.Outlined.Search,
                        contentDescription = "",
                        tint = Color.White)
                }
                Text(text = weather.city,
                    color = Color.White,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold

                )

                Text(text = "Today",
                    color = Color.White,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(end = 7.dp)

                )
            }


            Image(painter = painterResource(id = weather.weatherType.iconRes),
                contentDescription = "",
                alignment = Alignment.Center,
                modifier = Modifier.size(200.dp)
            )

            Text(text = weather.weatherType.weatherDesc,
                color = Color.White,
                fontSize = 40.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(text = "${weather.temp} C",
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

                Humidity(R.drawable.ic_pressure, "${weather.airPressure}hpa")
                Humidity(R.drawable.ic_drop, "${weather.humidity}%")
                Humidity(R.drawable.ic_wind,"${weather.windSpeed}km/h")
            }

        }
    }
    if(alertDialogShow){
        AlertDialog(onDismissRequest = { alertDialogShow=false }) {
            Column {
                TextField(value = text ,
                    onValueChange ={text=it},
                    label = { Text(text = "Enter City:")},
                    maxLines = 1)
                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    cityEnteredChange(text)
                    text=""
                    alertDialogShow=false }) {
                    Text(text = "Submit")
                }
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