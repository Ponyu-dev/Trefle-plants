package com.ponyu.botanical.fragments.plant_detail

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.ponyu.botanical.R
import com.ponyu.botanical.data.remote.plant.MainSpecies
import com.ponyu.botanical.databinding.FragmentPlantInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantInfoFragment : Fragment() {
    private var plantId: Int? = null

    // TODO: Maybe. Look at the documentation, maybe you can use something else.
    private val plantInfoViewModel by activityViewModels<PlantInfoViewModel>()

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

        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerPlantDetails) as NavHostFragment
        binding?.bottomNavigationView?.let {
            NavigationUI.setupWithNavController(it, navHostFragment.navController)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                plantInfoViewModel.plantInfo.collect { plantInfoData ->
                    plantInfoData?.mainSpecies?.let { mainSpecies ->
                        updateMainInfo(mainSpecies)
                    }
                }
            }
        }

        plantInfoViewModel.loadPlantInfo(plantId ?: PLANT_ID_COCONUT)
    }

    private fun updateMainInfo(
        mainSpecies: MainSpecies
    ){
        withBinding {
            textViewPlantScientificName.text = mainSpecies.scientificName
            textViewPlantCommonName.text = mainSpecies.commonName
            textViewObservations.text = "\uD83C\uDF0D ${mainSpecies.observations}"

            val spannable1 = SpannableString("\uD83D\uDCD7 ${mainSpecies.author} ${mainSpecies.year} - ")
            val spannable2 = SpannableString(mainSpecies.bibliography)
            spannable2.setSpan(StyleSpan(Typeface.ITALIC), 0, spannable2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            textViewPlantFirstPublish.text = SpannableStringBuilder(spannable1).append(spannable2)

            Glide.with(this@PlantInfoFragment)
                .load(mainSpecies.imageUrl)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_not_found)
                .centerCrop()
                .into(imageViewPlant)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        private const val PLANT_ID_COCONUT = 236068
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