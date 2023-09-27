package com.companysilas.pokemon.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.companysilas.core.domain.model.Pokemon
import com.companysilas.pokemon.framework.service.ApiService
import com.companysilas.pokemon.util.Constants

class PokemonPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Pokemon>() {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val offSet = params.key ?: Constants.STARTING_OFFSET_INDEX
        val loadSize = params.loadSize
        return try {

            val paging = apiService.pokemon(loadSize, offSet)

            LoadResult.Page(
                data = paging.results,
                prevKey = offSet - loadSize,
                nextKey = offSet + loadSize
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    @Suppress("TooGenericExceptionCaught")
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(
                LIMIT
            )
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}
