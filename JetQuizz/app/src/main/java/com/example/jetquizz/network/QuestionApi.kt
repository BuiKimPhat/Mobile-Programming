package com.example.jetquizz.network

import com.example.jetquizz.model.Question
import retrofit2.http.GET

interface QuestionApi {
    @GET("")
    suspend fun getAllQuestions(): Question
}