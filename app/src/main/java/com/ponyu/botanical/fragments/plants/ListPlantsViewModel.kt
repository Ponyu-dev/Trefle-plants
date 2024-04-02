package com.ponyu.botanical.fragments.plants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.ponyu.botanical.domain.PlantsRepository
import com.ponyu.botanical.ui.PlantItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ListPlantsViewModel @Inject constructor(
    private val plantsRepository: PlantsRepository
) : ViewModel() {

    val plantItemsUiStates = plantsRepository.getPlants()
        .map { pagingData ->
            pagingData.map { plantData ->
                PlantItemUiState(plantData)
            }
        }.cachedIn(viewModelScope)
}