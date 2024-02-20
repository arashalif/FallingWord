package com.arashaghelifar.fallingword.di

import com.arashaghelifar.fallingword.data.GameRepositoryImpl
import com.arashaghelifar.fallingword.data.remote.WordsDataSource
import com.arashaghelifar.fallingword.data.remote.WordsDataSourceImpl
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGameRepository(repository: GameRepositoryImpl): GameRepository

    @Binds
    abstract fun bindWordsApi(repository: WordsDataSourceImpl): WordsDataSource

}