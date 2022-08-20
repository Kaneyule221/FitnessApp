package com.example.repfluentv2.Models

import com.google.gson.JsonObject

data class Exerciseses(val exercises: List<ExercisesItem>?,
                       val api: Api)


data class ExercisesesJson(val exercises: JsonObject,
                       val api: Api)