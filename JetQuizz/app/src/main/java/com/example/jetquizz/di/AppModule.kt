package com.example.jetquizz.di

import com.example.jetquizz.network.QuestionApi
import com.example.jetquizz.repository.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideQuestionRepository(api: QuestionApi)
        = QuestionRepository(api)

    fun provideQuestionApi(): QuestionApi{
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionApi::class.java)
    }
}