package com.arashaghelifar.fallingword.data.remote.model

import com.google.gson.annotations.SerializedName

data class WordDataModel(
    @SerializedName("text_eng")
    val textEng: String,
    @SerializedName("text_spa")
    val textSpa: String
)




