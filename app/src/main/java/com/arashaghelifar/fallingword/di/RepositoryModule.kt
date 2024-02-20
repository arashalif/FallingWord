package com.arashaghelifar.fallingword.di

import com.arashaghelifar.fallingword.data.GameRepositoryImpl
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

}