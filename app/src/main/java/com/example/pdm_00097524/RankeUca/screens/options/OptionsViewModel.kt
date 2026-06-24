package com.example.pdm_00097524.RankeUca.screens.options

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import com.example.pdm_00097524.RankeUca.data.repository.OptionRepository
import com.example.pdm_00097524.RankeUca.model.Option
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pdm_00097524.RankeUca.RankeUcaApplication
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY

class OptionsViewModel (
    private val optionRepository: OptionRepository,
    private val questionId: Int
) : ViewModel(){

    val options: StateFlow<List<Option>> =
        optionRepository.getOptions(questionId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun addOption(value: String, imageUrl: String) {
        viewModelScope.launch {
            optionRepository.addOption(value, imageUrl,questionId)
        }
    }

    fun deleteOption(option: Option){
        viewModelScope.launch {
            optionRepository.deleteOption(option)
        }
    }

    companion object {

        fun provideFactory(questionId: Int) = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RankeUcaApplication
                OptionsViewModel(app.appProvider.provideOptionRepository(), questionId)
            }
        }
    }

}