package com.powerhouseweatherai.sumit.di

import com.powerhouseweatherai.sumit.data.remote.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}