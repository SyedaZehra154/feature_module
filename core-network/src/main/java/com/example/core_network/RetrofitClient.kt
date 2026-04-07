package com.example.core.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Provides @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

//“NetworkModule Hilt ka module hai jo network dependencies provide karta hai.
// @Module aur @InstallIn annotations define karte hain ke yeh dependencies app level par available hongi.
// OkHttpClient HTTP requests handle karta hai aur logging interceptor debugging ke liye use hota hai.
// Retrofit API calls ke liye use hota hai jisme base URL aur Gson converter configure hota hai.
// @Singleton ensure karta hai ke sirf ek hi instance poori app mein use ho
//Providers wo functions hote hain jo Hilt ko batate hain ke kisi object ko kaise create karna hai.

//NetworkModule provides OkHttp and Retrofit using Hilt,
// where @Module defines providers, @InstallIn specifies lifecycle,
// and @Singleton ensures a single instance across the app

//@Module Hilt ko batata hai ke yeh class dependencies provide karegi.
//@InstallIn batata hai ke yeh module kis lifecycle/component mein available hoga.