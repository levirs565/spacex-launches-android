package com.levirs.spacexlaunches.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.levirs.spacexlaunches.data.remote.RemoteApi
import com.levirs.spacexlaunches.data.utils.OffsetDateTimeGsonDeserializer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.threeten.bp.OffsetDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .build()

    @Provides
    fun provideGsonConverter() = GsonConverterFactory.create(
        GsonBuilder()
            .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeGsonDeserializer())
            .create()
    )

    @Provides
    fun provideRemoteApi(okHttpClient: OkHttpClient, converter: GsonConverterFactory) = Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com/v4")
        .client(okHttpClient)
        .addConverterFactory(converter)
        .build()
        .create(RemoteApi::class.java)
}