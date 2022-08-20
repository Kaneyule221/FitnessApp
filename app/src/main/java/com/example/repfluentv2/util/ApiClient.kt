package com.example.repfluentv2.util

import com.example.repfluentv2.constants.constantLoginVar
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.ExerciseRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    @Singleton
    @Provides
    fun provideExerciseRepository(api: ApiService) = ExerciseRepository(api)


    @Singleton
    @Provides
    fun getApiService(): ApiService{
        return Retrofit.Builder()
                .baseUrl(constantLoginVar.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient())
                .build().create(ApiService::class.java)

    }

    private fun okhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthIntercepter())
            .build()
    }
}