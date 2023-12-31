package com.powerhouseweatherai.sumit.di

import android.content.Context
import com.powerhouseweatherai.sumit.data.local.WeatherDetailsDatabase
import com.powerhouseweatherai.sumit.data.remote.ApiServices
import com.powerhouseweatherai.sumit.data.repository.WeatherRepositoryImpl
import com.powerhouseweatherai.sumit.domain.repository.WeatherRepository
import com.powerhouseweatherai.sumit.responsehandler.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule  {
    @Singleton
    @Provides
    fun provideNetworkOperations(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): ApiServices {
        return retrofitBuilder.client(okHttpClient).build().create(ApiServices::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        apiServices: ApiServices,
        responseHandler: ResponseHandler,
        weatherDetailsDatabase: WeatherDetailsDatabase,
        @ApplicationContext context: Context
    ): WeatherRepository {
        return WeatherRepositoryImpl(apiServices,responseHandler,weatherDetailsDatabase.weatherDetailsDao(), context = context)
    }

}