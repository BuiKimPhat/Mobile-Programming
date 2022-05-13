package com.example.jetquizz.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetquizz.data.DataOrException
import com.example.jetquizz.model.QuestionItem
import com.example.jetquizz.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel
    @Inject constructor(private val repository: QuestionRepository): ViewModel(){
    val data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>>
        = mutableStateOf(DataOrException(null, false, Exception("")))

    init {

    }

    private fun getAllQuestions(){
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuestions()

            if (data.value.data.toString().isNotEmpty())
                data.value.loading = false
        }
    }

    private fun getTotalQuestionCount(): Int {
        return data.value.data?.toMutableList()?.size!!
    }
}