package com.example.repfluentv2.Models

import com.google.gson.annotations.SerializedName

data class Api(
    @SerializedName("call-count-Limit-hour")
    val callCountLimitHour: Int = 0,
    val videoAccess: String = "",
    @SerializedName("guid-tier")
               val guidTier: Int = 0,
    @SerializedName("call-count-within-minute")
               val callCountWithinMinute: Int = 0,
    @SerializedName("call-count-within-hour")
               val callCountWithinHour: Int = 0,
    @SerializedName("call-count-limit-minute")
               val callCountLimitMinute: Int = 0)