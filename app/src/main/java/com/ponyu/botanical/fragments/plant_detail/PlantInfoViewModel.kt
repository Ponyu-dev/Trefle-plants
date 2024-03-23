package com.ponyu.botanical.fragments.plant_detail

import androidx.lifecycle.ViewModel
import com.ponyu.botanical.data.remote.plant.Flower
import com.ponyu.botanical.data.remote.plant.Foliage
import com.ponyu.botanical.data.remote.plant.FruitOrSeed
import com.ponyu.botanical.data.remote.plant.PlantInfoData
import com.ponyu.botanical.data.remote.plant.Specifications
import com.ponyu.botanical.domain.PlantRepository
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

    private val _specifications = MutableStateFlow<Specifications?>(null)
    val specifications = _specifications.asStateFlow()

    private val _flowerInfo = MutableStateFlow<Flower?>(null)
    val flowerInfo = _flowerInfo.asStateFlow()

    private val _foliageInfo = MutableStateFlow<Foliage?>(null)
    val foliageInfo = _foliageInfo.asStateFlow()

    private val _fruitOrSeedInfo = MutableStateFlow<FruitOrSeed?>(null)
    val fruitOrSeedInfo = _fruitOrSeedInfo.asStateFlow()

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun loadPlantInfo(plantId: Int) {
        viewModelScope.launch {
            val pairResult = plantInfoRepository.loadPlant(plantId)
            _plantInfo.value = pairResult.second?.data

            _specifications.value = _plantInfo.value?.mainSpecies?.specifications

            _flowerInfo.value = _plantInfo.value?.mainSpecies?.flower
            _foliageInfo.value = _plantInfo.value?.mainSpecies?.foliage
            _fruitOrSeedInfo.value = _plantInfo.value?.mainSpecies?.fruitOrSeed
        }
    }

    companion object {
        private const val TAG = "PlantInfoViewModel"
    }
}