package com.example.pdm_00097524.RankeUca.data.repositories.repositoryRoom

import com.example.pdm_00097524.RankeUca.data.local.database.dao.OptionDao
import com.example.pdm_00097524.RankeUca.data.local.database.entities.toModel
import com.example.pdm_00097524.RankeUca.data.model.Option
import com.example.pdm_00097524.RankeUca.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OptionRespositoryImpl (
    private val optionDao: OptionDao
) : OptionRepository {
    override fun getOptions(questionId: Int): Flow<List<Option>> {
        return optionDao.getAllOptions(questionId).map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun addOption(value: String, imageUrl: String, questionId: Int) {
        val option = Option(value = value, imageUrl = imageUrl, questionId = questionId, votes = 0)
        optionDao.insertOption(option.toEntity())
    }

    override suspend fun deleteOption(option: Option) {
        optionDao.deleteOption(option.toEntity())
    }

    override suspend fun updateOption(option: Option) {
        optionDao.updateOption(option.toEntity())
    }
}