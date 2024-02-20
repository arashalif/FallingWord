package com.arashaghelifar.fallingword.data.remote

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordsDataSource {
    suspend fun fetchWords(): Flow<BaseResponse<List<Word>>>

}