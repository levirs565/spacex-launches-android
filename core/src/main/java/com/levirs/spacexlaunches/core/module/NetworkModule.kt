package com.levirs.spacexlaunches.core.module

import com.google.gson.GsonBuilder
import com.levirs.spacexlaunches.core.data.remote.RemoteApi
import com.levirs.spacexlaunches.core.data.utils.OffsetDateTimeGsonDeserializer
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import org.threeten.bp.OffsetDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    private val mDomain = "api.spacexdata.com"

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .certificatePinner(
            CertificatePinner.Builder().run {
                add(mDomain, "sha256/3rWuRCFN0knGaXU31iJp+qSe2iOn7VBJwRRd0ZVe/14=")
                add(mDomain, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
                build()
            }
        )
        .build()

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder()
            .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeGsonDeserializer())
            .create()
    )

    @Provides
    fun provideRemoteApi(okHttpClient: OkHttpClient, converter: GsonConverterFactory):
        RemoteApi =
            Retrofit.Builder()
                .baseUrl("https://$mDomain/v4/")
                .client(okHttpClient)
                .addConverterFactory(converter)
                .build()
                .create(RemoteApi::class.java)
}
