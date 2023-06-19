package com.weather.data.datasorce.entity

import com.google.gson.annotations.SerializedName

data class WindDto(
    @SerializedName("speed") val speed: Double,
)
