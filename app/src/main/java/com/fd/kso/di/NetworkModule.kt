package com.fd.kso.di

import com.fd.kso.data.api.ApiService
import com.fd.kso.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    @Reusable
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return  retrofit.create(ApiService::class.java)
    }


    @Provides
    @Reusable
    internal fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}