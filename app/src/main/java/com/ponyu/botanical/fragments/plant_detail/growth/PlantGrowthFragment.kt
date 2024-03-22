package com.ponyu.botanical.fragments.plant_detail.growth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ponyu.botanical.R
import com.ponyu.botanical.fragments.plant_detail.PlantInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantGrowthFragment : Fragment() {

    companion object {
        private const val TAG = "PlantGrowthFragment"
    }

    // TODO: Maybe. Look at the documentation, maybe you can use something else.
    private val plantInfoViewModel by activityViewModels<PlantInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plant_growth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                plantInfoViewModel.plantInfo.collect { plantInfoData ->
                    plantInfoData?.mainSpecies?.let { mainSpecies ->
                        Log.d(TAG, "It is ${mainSpecies.scientificName} ")
                    }
                }
            }
        }
    }
}