package com.russialoses.app.di

import com.russialoses.app.BuildConfig
import com.russialoses.app.data.datasource.LossesDataSourceImpl
import com.russialoses.app.data.api.LosesApiService
import com.russialoses.app.domain.interfaces.LosesDataSource
import com.russialoses.app.util.Constants
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
object LosesRepositoryModule {

    @Provides
    fun providesBaseUrl(): String = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideLosesService(retrofit: Retrofit): LosesApiService =
        retrofit.create(LosesApiService::class.java)

    @Provides
    @Singleton
    fun provideLosesRemoteData(losesService: LosesApiService): LosesDataSource =
        LossesDataSourceImpl(losesService)
}