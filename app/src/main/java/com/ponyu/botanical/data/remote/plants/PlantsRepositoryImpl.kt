package com.ponyu.botanical.data.remote.plants

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ponyu.botanical.data.remote.PlantsService
import com.ponyu.botanical.domain.PlantsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlantsRepositoryImpl @Inject constructor(
    private val service: PlantsService
) : PlantsRepository {
    override fun getPlants(): Flow<PagingData<PlantData>> {
        return Pager(
            config = PagingConfig (
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { PlantsPagingDataSource(service) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}