package com.ponyu.botanical.fragments.plant_detail.specifications

import android.os.Bundle
import android.text.SpannableStringBuilder
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
import com.ponyu.botanical.data.remote.plant.Flower
import com.ponyu.botanical.data.remote.plant.Foliage
import com.ponyu.botanical.data.remote.plant.FruitOrSeed
import com.ponyu.botanical.data.remote.plant.Specifications
import com.ponyu.botanical.databinding.FragmentPlantSpecificationsBinding
import com.ponyu.botanical.fragments.plant_detail.PlantInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class PlantSpecificationsFragment : Fragment() {
    companion object {
        private const val TAG = "PlantSpecificationsFrag"
        private const val COMA = ", ";
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
                plantInfoViewModel.plantInfo.collect { plantInfo ->
                    plantInfo?.mainSpecies?.let { mainSpecies ->
                        updateEdiblePart(mainSpecies.ediblePart)
                        updateFlowers(mainSpecies.flower)
                        updateFoliage(mainSpecies.foliage)
                        updateFruits(mainSpecies.fruitOrSeed)
                        updateSpecificationInfo(mainSpecies.specifications)
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

    private fun getVisibleString (
        visible: Boolean?
    ) : String {
        return when (visible){
            true -> getString(R.string.visible)
            false -> getString(R.string.invisible)
            else -> getString(R.string.unknown)
        }
    }

    private fun getColorString (
        color: String?
    ) : String {
        return color ?: getString(R.string.unknown)
    }

    private fun updateFlowers(flower: Flower){
        withBinding {
            textViewFlowers.text = SpannableStringBuilder(getVisibleString(flower.conspicuous))
                .append(COMA)
                .append(getColorString(flower.color?.joinToString()))
                .append(COMA)
                .append(getString(R.string.flowers))
        }
    }

    private fun updateFoliage(foliage: Foliage){
        withBinding {
            textViewFoliage.text = SpannableStringBuilder(getVisibleString(foliage.leafRetention))
                .append(COMA)
                .append((foliage.texture ?: getString(R.string.unknown)).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                    else it.toString()
                })
                .append(COMA)
                .append(getColorString(foliage.color?.joinToString()))
                .append(COMA)
                .append(getString(R.string.foliage))
        }
    }

    private fun updateFruits(fruits: FruitOrSeed){
        withBinding {
            textViewFruits.text = SpannableStringBuilder(getVisibleString(fruits.conspicuous))
                .append(COMA)
                .append(getColorString(fruits.color?.joinToString()))
                .append(COMA)
                .append(getString(R.string.fruits))
        }
    }

    private fun updateSpecificationInfo(specifications: Specifications){
        withBinding {
            textLigneousType.text = getString(R.string.ligneous_type, specifications.ligneousType)
            textAverageHeight.text = getString(R.string.average_height, specifications.averageHeight.cm)
            textMaximumHeight.text = getString(R.string.maximum_height, specifications.maximumHeight.cm)
            textNitrogenFixation.text = getString(R.string.nitrogen_fixation, specifications.nitrogenFixation)
            textShapeAndOrientation.text = getString(R.string.shape_and_orientation, specifications.shapeAndOrientation)
            textToxicity.text = getString(R.string.toxicity, specifications.toxicity)
        }
    }
}