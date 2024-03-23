package com.ponyu.botanical.fragments.plant_detail.specifications

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
import com.google.android.material.chip.Chip
import com.ponyu.botanical.R
import com.ponyu.botanical.databinding.FragmentPlantInfoBinding
import com.ponyu.botanical.databinding.FragmentPlantSpecificationsBinding
import com.ponyu.botanical.fragments.plant_detail.PlantInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantSpecificationsFragment : Fragment() {
    companion object {
        private const val TAG = "PlantSpecificationsFrag"
    }

    // TODO: Maybe. Look at the documentation, maybe you can use something else.
    private val plantInfoViewModel by activityViewModels<PlantInfoViewModel>()


    private var binding: FragmentPlantSpecificationsBinding? = null
    private inline fun withBinding(block: FragmentPlantSpecificationsBinding.() -> Unit) {
        binding?.block()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantSpecificationsBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                plantInfoViewModel.plantInfo.collect { plantInfoData ->
                    plantInfoData?.mainSpecies?.let { mainSpecies ->
                        updateEdiblePart(mainSpecies.ediblePart)
                    }
                }
            }
        }
    }

    private fun updateEdiblePart(
        ediblePart: List<String?>?
    ){
        withBinding {
            if (ediblePart.isNullOrEmpty()) {
                textViewNotEdibleParts.visibility = View.VISIBLE
            }
            else {
                textViewNotEdibleParts.visibility = View.GONE

                ediblePart.forEach {
                    Chip(context).apply {
                        text = it ?: getString(R.string.unknown)
                        isClickable = false
                        isCheckable = false
                        chipGroupEdibleParts.addView(this)
                    }
                }
            }
        }
    }
}