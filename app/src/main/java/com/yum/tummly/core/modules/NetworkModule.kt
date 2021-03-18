package com.yum.tummly.core.modules

import com.yum.tummly.data.api.Endpoints
import com.yum.tummly.data.api.RecipeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttp)
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }


    @Provides
    fun provideRestaurantsApi(retrofit: Retrofit): RecipeApi {
        return retrofit.create(RecipeApi::class.java)
    }

}