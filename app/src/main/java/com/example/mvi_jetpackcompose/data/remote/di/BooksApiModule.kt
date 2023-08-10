package com.example.mvi_jetpackcompose.data.remote.di

import com.example.mvi_jetpackcompose.data.remote.api.ApiEndpoints
import com.example.mvi_jetpackcompose.data.remote.api.BooksApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object BooksApiModule {

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): BooksApi {
        return builder
            .build()
            .create(BooksApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiEndpoints.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}
