package com.example.newcobybase.di

import com.example.newcobybase.BuildConfig
import com.example.newcobybase.data.api.JsonPlaceHolderApi
import com.example.newcobybase.data.api.StackOverFlowApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideJsonPlaceHolderAPI(@Named("JsonPlaceHolderSite") retrofit: Retrofit): JsonPlaceHolderApi {
        return retrofit.create(JsonPlaceHolderApi::class.java)
    }

    @Provides
    fun provideStackOverFlowAPI(@Named("StackOverFlowSite") retrofit: Retrofit): StackOverFlowApi {
        return retrofit.create(StackOverFlowApi::class.java)
    }

    @Provides
    @Singleton
    @Named("JsonPlaceHolderSite")
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {

        return Retrofit.Builder().addConverterFactory(moshiConverterFactory)
            .baseUrl(BuildConfig.BASE_URL_JSON_PLACE_HOLDER)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("StackOverFlowSite")
    fun provideRetrofitStackOverFlow(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder().addConverterFactory(moshiConverterFactory)
            .baseUrl(BuildConfig.BASE_URL_STACK_OVER_FLOW)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.interceptors().add(httpLoggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

}