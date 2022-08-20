package com.example.repfluentv2.util

import com.example.repfluentv2.Models.*
import com.example.repfluentv2.constants.constantLoginVar
import retrofit2.Call
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface ApiService {

    @POST(constantLoginVar.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse


    @GET(constantLoginVar.GetExUrl)
    suspend fun fetchPosts(@Query("exercisename") query: String): Exerciseses

    @GET(constantLoginVar.GetExUrl)
    suspend fun fetchPosts2(@Query("exercisename") query: String): ExercisesesJson


}