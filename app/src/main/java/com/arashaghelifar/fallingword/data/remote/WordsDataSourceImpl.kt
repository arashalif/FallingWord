package com.arashaghelifar.fallingword.data.remote

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.data.remote.mapper.MapToBaseResponse.mapToBaseResponse
import com.arashaghelifar.fallingword.data.remote.mapper.WordDataModelsToWordsMapper
import com.arashaghelifar.fallingword.domain.model.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WordsDataSourceImpl @Inject constructor(
    private val wordsApi: WordsApi
) : WordsDataSource {

    override suspend fun fetchWords(): Flow<BaseResponse<List<Word>>> = flow {
        val response = wordsApi.fetchEnglishSpanishWords()
        emit(response)
    }.map { response ->

        response.mapToBaseResponse {
            WordDataModelsToWordsMapper.map(it)
        }
    }.catch {
        emit(BaseResponse.Error(it.message.toString()))
    }

}