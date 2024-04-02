package com.ponyu.botanical.fragments.plant_detail.images

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
import com.ponyu.botanical.data.remote.plant.Images
import com.ponyu.botanical.data.remote.plant.ImagesData
import com.ponyu.botanical.databinding.FragmentPlantImagesBinding
import com.ponyu.botanical.fragments.plant_detail.PlantInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantImagesFragment : Fragment() {

    companion object {
        private const val TAG = "PlantImagesFragment"
    }

    // TODO: Maybe. Look at the documentation, maybe you can use something else.
    private val plantInfoViewModel by activityViewModels<PlantInfoViewModel>()

    private var binding: FragmentPlantImagesBinding? = null
    private inline fun withBinding(block: FragmentPlantImagesBinding.() -> Unit) {
        binding?.block()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantImagesBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                plantInfoViewModel.plantInfo.collect { plantInfoData ->
                    plantInfoData?.mainSpecies?.let { mainSpecies ->
                        updateImages(mainSpecies.images)
                    }
                }
            }
        }
    }

    private fun updateImages(images: Images){
        withBinding {
            if(images.flower.isNullOrEmpty()){
                viewStubPlantImageFlower.visibility = View.VISIBLE
            } else {
                viewStubPlantImageFlower.visibility = View.GONE
                recyclerViewPlantImageFlower.adapter = getPlantImageAdapter(images.flower)
            }

            if(images.fruit.isNullOrEmpty()){
                viewStubPlantImageFruit.visibility = View.VISIBLE
            } else {
                viewStubPlantImageFruit.visibility = View.GONE
                recyclerViewPlantImageFruit.adapter = getPlantImageAdapter(images.fruit)
            }

            if(images.habit.isNullOrEmpty()){
                viewStubPlantImageHabit.visibility = View.VISIBLE
            } else {
                viewStubPlantImageHabit.visibility = View.GONE
                recyclerViewPlantImageHabit.adapter = getPlantImageAdapter(images.habit)
            }

            if(images.leaf.isNullOrEmpty()){
                viewStubPlantImageLeaf.visibility = View.VISIBLE
            } else {
                viewStubPlantImageLeaf.visibility = View.GONE
                recyclerViewPlantImageLeaf.adapter = getPlantImageAdapter(images.leaf)
            }

            if(images.bark.isNullOrEmpty()){
                viewStubPlantImageBark.visibility = View.VISIBLE
            } else {
                viewStubPlantImageBark.visibility = View.GONE
                recyclerViewPlantImageBark.adapter = getPlantImageAdapter(images.bark)
            }

            if(images.other.isNullOrEmpty()){
                viewStubPlantImageOther.visibility = View.VISIBLE
            } else {
                viewStubPlantImageOther.visibility = View.GONE
                recyclerViewPlantImageOther.adapter = getPlantImageAdapter(images.other)
            }
        }
    }

    private fun getPlantImageAdapter (
        images: List<ImagesData?>
    ) : PlantImageAdapter {
        return PlantImageAdapter(images) {
            Log.d(TAG, "Show images big")
        }
    }
}