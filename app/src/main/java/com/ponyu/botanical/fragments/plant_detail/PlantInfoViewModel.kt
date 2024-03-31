package com.ponyu.botanical.fragments.plant_detail

import androidx.lifecycle.ViewModel
import com.ponyu.botanical.data.remote.plant.Flower
import com.ponyu.botanical.data.remote.plant.Foliage
import com.ponyu.botanical.data.remote.plant.FruitOrSeed
import com.ponyu.botanical.data.remote.plant.PlantInfoData
import com.ponyu.botanical.data.remote.plant.Specifications
import com.ponyu.botanical.domain.PlantRepository
import com.ponyu.botanical.fragments.plant_detail.images.PlantImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantInfoViewModel @Inject constructor (
    private val plantInfoRepository: PlantRepository
) : ViewModel() {

    private val _plantInfo = MutableStateFlow<PlantInfoData?>(null)
    val plantInfo = _plantInfo.asStateFlow()

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun loadPlantInfo(plantId: Int) {
        viewModelScope.launch {
            val pairResult = plantInfoRepository.loadPlant(plantId)
            _plantInfo.value = pairResult.second?.data


        }
    }

    companion object {
        private const val TAG = "PlantInfoViewModel"
    }
}