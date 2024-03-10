package com.ponyu.botanical.fragments.plant_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ponyu.botanical.databinding.FragmentPlantInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantInfoFragment : Fragment() {

    private var plantId: Int? = null

    private val plantInfoViewModel: PlantInfoViewModel by viewModels()

    private var binding: FragmentPlantInfoBinding? = null
    private inline fun withBinding(block: FragmentPlantInfoBinding.() -> Unit) {
        binding?.block()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantInfoBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withBinding {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    plantInfoViewModel.plantInfo.collect {
                        it?.data.let {plant ->
                            Log.d(TAG, "commonName ${plant?.mainSpecies?.commonName}")

                            textView.text = plant?.mainSpecies?.commonName
                        }
                    }
                }
            }
        }

        plantInfoViewModel.loadPlantInfo(plantId ?: PLANT_ID_COCONUT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        private const val PLANT_ID_COCONUT = 236068
        private const val TAG = "PlantInfoFragment"
        private const val ARG_PLANT_ID = "plantId"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param plantIdArg Parameter for .
         * @return A new instance of fragment PlantInfoFragment.
         */
        @JvmStatic
        fun newInstance(plantIdArg: Int) =
            PlantInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PLANT_ID, plantIdArg)
                }
            }
    }
}