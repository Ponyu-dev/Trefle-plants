package com.ponyu.botanical.fragments.plant_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ponyu.botanical.data.remote.plant.PlantInfo
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

    private val _plantInfo = MutableStateFlow<PlantInfo?>(null)
    val plantInfo = _plantInfo.asStateFlow()

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun loadPlantInfo(plantId: Int) {
        Log.d(TAG, "loadPlantInfo for plantId $plantId")
        viewModelScope.launch {
            val pairResult = plantInfoRepository.loadPlant(plantId)

            Log.d(TAG, "result = ${pairResult.first}")

            _plantInfo.value = pairResult.second
        }
    }

    companion object {
        private const val TAG = "PlantInfoViewModel"
    }
}