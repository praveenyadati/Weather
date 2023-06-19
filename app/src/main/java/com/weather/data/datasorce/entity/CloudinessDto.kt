package com.weather.data.datasorce.entity

import com.google.gson.annotations.SerializedName

data class CloudinessDto(
    @SerializedName("all") val cloudiness: Int
)