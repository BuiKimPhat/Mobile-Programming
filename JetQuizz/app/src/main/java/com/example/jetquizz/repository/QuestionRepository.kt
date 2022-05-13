package com.example.jetquizz.repository

import com.example.jetquizz.data.DataOrException
import com.example.jetquizz.model.QuestionItem
import com.example.jetquizz.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionApi) {
    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions():
            DataOrException<ArrayList<QuestionItem>, Boolean, Exception>{
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty())
                dataOrException.loading = false
        } catch (exception: Exception){
            dataOrException.e = exception
        }
        return dataOrException
    }
}