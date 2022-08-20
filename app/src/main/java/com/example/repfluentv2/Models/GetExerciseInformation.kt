package com.example.repfluentv2.Models

import com.google.gson.annotations.SerializedName

data class GetExerciseInformation(
    @SerializedName("Exercise_Name")
    var Exercise_Name:String= "",
    @SerializedName("Exercise_Id")
    var Exercise_Id: Int= 0,
    @SerializedName("URL")
    var ExerciseUrl: String= "",
    @SerializedName("Apparatus_Abbreviation")
    var Apparatus: String = "",
    @SerializedName("Difficulty_Low")
    var Difficulty_Low:Int= 0,
    @SerializedName("Difficulty_High")
    var Difficulty_High:Int= 0,
    @SerializedName("Instructions_Preparation")
    var Instructions_Preparation:String = "",
    @SerializedName("Instructions_Execution")
    var Instructions_Execution:String = ""
)
