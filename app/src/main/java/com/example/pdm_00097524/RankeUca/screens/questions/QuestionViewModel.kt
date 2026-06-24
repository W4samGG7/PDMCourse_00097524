package com.example.pdm_00097524.RankeUca.screens.questions


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pdm_00097524.RankeUca.RankeUcaApplication
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.pdm_00097524.RankeUca.data.repository.QuestionRepository
import com.example.pdm_00097524.RankeUca.model.Question

class QuestionViewModel (
    private val questionRepository: QuestionRepository,
) : ViewModel(){

    val questions: StateFlow<List<Question>> =
        questionRepository.getQuestions()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun addQuestion(title: String) {
        viewModelScope.launch {
            questionRepository.addQuestion(title = title)
        }
    }

    fun deleteQuestion(question: Question){
        viewModelScope.launch {
            questionRepository.deleteQuestion(question)
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RankeUcaApplication
                QuestionViewModel(app.appProvider.provideQuestionRepository())
            }
        }
    }

}