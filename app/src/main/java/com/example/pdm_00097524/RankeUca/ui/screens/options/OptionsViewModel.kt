package com.example.pdm_00097524.RankeUca.ui.screens.options

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryRoom.OptionRepository
import com.example.pdm_00097524.RankeUca.data.model.Option
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pdm_00097524.RankeUca.RankeUcaApplication
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryOfflineFirst.QuestionOfflineFirstRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OptionsViewModel (
    private val questionOfflineFirstRepository: QuestionOfflineFirstRepository,
    private val questionId: Int
) : ViewModel(){

    val options: StateFlow<List<Option>> =
        questionOfflineFirstRepository.getOptions(questionId)
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
        refreshOptions()
    }
    fun refreshOptions(){
        viewModelScope.launch {
            _error.value = null
            _refreshing.value = true

            questionOfflineFirstRepository.refreshQuestionOptions(questionId)
                .onSuccess {

                }
                .onFailure { error ->
                    _error.value = "Error al recargar las opciones: ${error}"
                }

            _refreshing.value = false

        }
    }

    fun addOption(value: String, imageUrl: String) {
        viewModelScope.launch {
            questionOfflineFirstRepository.createOption(questionId,value, imageUrl)
        }
    }

    fun deleteOption(option: Option){
        viewModelScope.launch {
            questionOfflineFirstRepository.deleteOption(option.id)
        }
    }

    fun updateOption(option: Option){
        viewModelScope.launch {
            questionOfflineFirstRepository.updateOption(
                id = option.id,
                value = option.value,
                imageUrl = option.imageUrl,
                questionId = null
            )
        }
    }

    companion object {

        fun provideFactory(questionId: Int) = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RankeUcaApplication
                OptionsViewModel(app.appProvider.provideQuestionOfflineFirstRepository(), questionId)
            }
        }
    }

}