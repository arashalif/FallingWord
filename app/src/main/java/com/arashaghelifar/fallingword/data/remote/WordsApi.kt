package com.arashaghelifar.fallingword.data.remote

import com.arashaghelifar.fallingword.data.remote.model.WordDataModel
import retrofit2.Response
import retrofit2.http.GET

interface WordsApi {
    @GET(ApiConstants.WORDS_PATH)
    suspend fun fetchEnglishSpanishWords(): Response<List<WordDataModel>>
}