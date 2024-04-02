package com.ponyu.botanical.fragments.plants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.ponyu.botanical.data.remote.plants.PlantFilterModel
import com.ponyu.botanical.domain.PlantsRepository
import com.ponyu.botanical.fragments.plants.adapter.PlantItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ListPlantsViewModel @Inject constructor(
    private val plantsRepository: PlantsRepository
) : ViewModel() {

    private val plantFilterFlow = MutableStateFlow(PlantFilterModel())

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val plantItemsUiStates =
        plantFilterFlow.debounce(300).flatMapLatest { filter ->
            plantsRepository.getPlants(filter)
                .map { pagingData ->
                    pagingData.map { plantData ->
                        PlantItemUiState(plantData)
                    }
                }.cachedIn(viewModelScope)
        }

    fun search(searchQuery: String) {
        plantFilterFlow.update {
            it.copy(searchQuery = searchQuery)
        }
    }
}