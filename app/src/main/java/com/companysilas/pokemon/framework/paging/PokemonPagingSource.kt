package com.companysilas.pokemon.framework.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.companysilas.core.domain.model.Pokemon
import com.companysilas.pokemon.framework.service.ApiService
import java.io.IOException

internal class PokemonPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Pokemon>() {

    private val searchPokemon: String = ""

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val position = params.key ?: POKEMON_STARTING_OFFSET
        val loadSize = if (searchPokemon.isNullOrEmpty()) params.loadSize else SEARCH_LOAD_SIZE
        return try {
            val data = apiService.pokemon(position * POKEMON_OFFSET)
            val filteredData = if (!searchPokemon.isNullOrEmpty()) {
                data.results.filter { it.name.contains(searchPokemon, true) ?: false }
            } else {
                data.results
            }
            LoadResult.Page(
                data = filteredData,
                prevKey = if (position == POKEMON_STARTING_OFFSET) null else position - 1,
                nextKey = if (filteredData.isEmpty()) null else position + 1
            )
        } catch (throwable: Throwable) {
            Log.d("ErrorPokemon", throwable.message.toString())
            var exception = throwable
            if (exception is IOException) {
                exception = IOException("Please check internet connection")
            }
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(LIMIT)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 1
        const val POKEMON_STARTING_OFFSET = 0
        const val POKEMON_OFFSET = 60
        const val SEARCH_LOAD_SIZE = 100
    }
}
