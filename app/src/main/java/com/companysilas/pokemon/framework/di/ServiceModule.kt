package com.companysilas.pokemon.framework.di

import com.companysilas.pokemon.framework.service.ApiService
import com.companysilas.pokemon.util.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val serviceModule = module {
    factory { provideForecastApi(get()) }
    factory { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideForecastApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
