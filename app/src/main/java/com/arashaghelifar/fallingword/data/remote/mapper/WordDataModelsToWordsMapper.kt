package com.arashaghelifar.fallingword.data.remote.mapper

import com.arashaghelifar.fallingword.data.remote.model.WordDataModel
import com.arashaghelifar.fallingword.domain.model.Word

object WordDataModelsToWordsMapper {

    fun map(wordDataModels: List<WordDataModel>): List<Word> {
        return wordDataModels.map {
            mapToDomain(it)
        }
    }

    private fun mapToDomain(wordDataModel: WordDataModel): Word = Word(
        mainWord = wordDataModel.textEng,
        answeredWord = wordDataModel.textSpa
    )

}
