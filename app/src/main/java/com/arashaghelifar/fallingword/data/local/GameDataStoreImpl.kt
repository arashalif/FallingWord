package com.arashaghelifar.fallingword.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.model.Stats
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : GameDataStore {

    private object PreferencesKeys {
        val STATS_DATASTORE_KEY = stringPreferencesKey("STATS_DATASTORE_KEY")
        val GAME_DATASTORE_KEY = stringPreferencesKey("GAME_DATASTORE_KEY")
    }

    private val gson = Gson()

    override fun getStats(): Flow<BaseResponse<Stats>> = flow {
        emit(BaseResponse.Loading)
        emitAll(
            dataStore.data
                .catch {
                    BaseResponse.Error(error = it.message ?: "Somethings wrong with IO")
                }
                .map {
                    if (it[PreferencesKeys.STATS_DATASTORE_KEY] != null) {
                        BaseResponse.Success(
                            gson.fromJson(
                                it[PreferencesKeys.STATS_DATASTORE_KEY],
                                Stats::class.java
                            )
                        )
                    } else {
                        BaseResponse.Success(Stats())
                    }
                })
    }


    override suspend fun saveStats(stats: Stats) {
        val json = gson.toJson(stats)
        dataStore.edit {
            it[PreferencesKeys.STATS_DATASTORE_KEY] = json
        }
    }

    override fun getPreviousGame(): Flow<BaseResponse<Game>> = flow {
        emit(BaseResponse.Loading)
        emitAll(
            dataStore.data
                .catch {
                    BaseResponse.Error(error = it.message ?: "Somethings wrong with IO")
                }
                .map {
                    if (it[PreferencesKeys.GAME_DATASTORE_KEY] != null) {
                        BaseResponse.Success(
                            gson.fromJson(
                                it[PreferencesKeys.GAME_DATASTORE_KEY],
                                Game::class.java
                            )
                        )
                    } else {
                        BaseResponse.Success(Game())
                    }
                })
    }

    override suspend fun savePreviousGame(game: Game) {
        val json = gson.toJson(game)
        dataStore.edit {
            it[PreferencesKeys.GAME_DATASTORE_KEY] = json
        }
    }

}