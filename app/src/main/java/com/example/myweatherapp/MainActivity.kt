package com.example.myweatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myweatherapp.components.WeatherCard
import com.example.myweatherapp.components.WeeklyWeatherCard
import com.example.myweatherapp.data.ApiInterface
import com.example.myweatherapp.data.Forecastday
import com.example.myweatherapp.data.Weather
import com.example.myweatherapp.ui.theme.DeepBlue
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var fetchWeather:MutableState<Weather> = remember {
                mutableStateOf(Weather(emptyList(),WeatherType.PartlyCloudy))
            }


            MyWeatherAppTheme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DeepBlue)
                ) {
                    LaunchedEffect(key1 = 1) {
                        fetchWeather.value =  fetchWeatherData()
                    }
                    WeatherCard(fetchWeather.value.weatherType)
                    WeeklyWeatherCard(fetchWeather.value.hourList)
                }

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyWeatherAppTheme {

    }
}

suspend fun fetchWeatherData() : Weather {



        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.weatherapi.com/v1/")
            .build()
            .create(ApiInterface::class.java)

        val result = retrofit.getWeatherData("01dee5675df54ad8bb600106241803","Toronto",1)

    Log.e("FetchWeatherData", result.location.country)

            val weathertype =WeatherType.fromWMO(result.current.condition.code)

    val hourList = result.forecast.forecastday.get(0).hour

    return Weather(hourList,weathertype)


}
