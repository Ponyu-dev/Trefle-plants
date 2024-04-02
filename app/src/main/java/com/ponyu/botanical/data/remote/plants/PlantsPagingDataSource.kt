package com.ponyu.botanical.data.remote.plants

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ponyu.botanical.data.remote.PlantsService

class PlantsPagingDataSource(
    private val plantsService: PlantsService,
    private val plantFilterModel: PlantFilterModel
) : PagingSource<Int, PlantData>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PlantData> {
        val page = params.key ?: STARTING_PAGE_INDEX
        try {
            val response = when {
                plantFilterModel.searchQuery.isNullOrEmpty() -> plantsService.getPlants(page)
                else -> plantsService.searchPlants(page, plantFilterModel.searchQuery)
            }

            //TODO it is necessary to add so that nextkey does not exceed the last page
            // this can be found in the model "links": {
            //        "first": "/api/v1/species?page=1",
            //        "last": "/api/v1/species?page=20865",
            //        "next": "/api/v1/species?page=3",
            //        "prev": "/api/v1/species?page=1",
            //        "self": "/api/v1/species?page=2"
            //    },

            return LoadResult.Page(
                data = response.data,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.data.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlantData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val TAG = "PlantsPagingDataSource"
        private const val STARTING_PAGE_INDEX = 1
    }
}