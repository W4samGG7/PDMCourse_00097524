package com.example.pdm_00097524.RankeUca.ui.screens.questions


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
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryRoom.QuestionRepository
import com.example.pdm_00097524.RankeUca.data.model.Question
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryOfflineFirst.QuestionOfflineFirstRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuestionViewModel (
    private val questionRepositoryOfflineFirstRepository: QuestionOfflineFirstRepository,
) : ViewModel(){

    val questions: StateFlow<List<Question>> =
        questionRepositoryOfflineFirstRepository.getQuestions()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    private val _refreshing = MutableStateFlow<Boolean>(false)

    val refreshing = _refreshing.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)

    val error = _error.asStateFlow()


    init {
        refreshQuestions()
    }

    fun refreshQuestions(){
        viewModelScope.launch {
            _error.value = null
            _refreshing.value = true

            questionRepositoryOfflineFirstRepository.refresh()
                .onSuccess {

                }
                .onFailure { error ->
                    _error.value = "Error al cargar las preguntas: ${error}"
                }

            _refreshing.value = false

        }
    }
    fun addQuestion(title: String) {
        viewModelScope.launch {
            questionRepositoryOfflineFirstRepository.createQuestion(title = title)
        }
    }

    fun deleteQuestion(question: Question){
        viewModelScope.launch {
            questionRepositoryOfflineFirstRepository.deleteQuestion(question.id)
        }
    }
    fun updateQuestion(question: Question){
        viewModelScope.launch {
            questionRepositoryOfflineFirstRepository.updateQuestion(question.id, question.title)
        }
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RankeUcaApplication
                QuestionViewModel(app.appProvider.provideQuestionOfflineFirstRepository())
            }
        }
    }

}