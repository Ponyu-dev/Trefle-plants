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
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.ponyu.botanical.R
import com.ponyu.botanical.data.remote.plant.MainSpecies
import com.ponyu.botanical.databinding.FragmentPlantInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantInfoFragment : Fragment() {
    //TODO I think this can be moved somewhere, but I don't understand where.
    private val navigationFromSpecifications by lazy {
        mapOf(
            getString(R.string.tab_title_growth) to R.id.action_plantSpecificationsFragment_to_plantGrowthFragment,
            getString(R.string.tab_title_images) to R.id.action_plantSpecificationsFragment_to_plantImagesFragment
        )
    }

    private val navigationFromGrowth by lazy {
        mapOf(
            getString(R.string.tab_title_specifications) to R.id.action_plantGrowthFragment_to_plantSpecificationsFragment,
            getString(R.string.tab_title_images) to R.id.action_plantGrowthFragment_to_plantImagesFragment
        )
    }

    private val navigationFromImages by lazy {
        mapOf(
            getString(R.string.tab_title_specifications) to R.id.action_plantImagesFragment_to_plantSpecificationsFragment,
            getString(R.string.tab_title_growth) to R.id.action_plantImagesFragment_to_plantGrowthFragment
        )
    }

    private val navigationTabs by lazy {
        mapOf(
            getString(R.string.tab_title_specifications) to navigationFromSpecifications,
            getString(R.string.tab_title_growth) to navigationFromGrowth,
            getString(R.string.tab_title_images) to navigationFromImages
        )
    }
    //TODO I think this can be moved somewhere, but I don't understand where.

    private var tabSelectedName = ""

    private var plantId: Int? = null

    // TODO: Maybe. Look at the documentation, maybe you can use something else.
    private val plantInfoViewModel by activityViewModels<PlantInfoViewModel>()

    private var binding: FragmentPlantInfoBinding? = null
    private inline fun withBinding(block: FragmentPlantInfoBinding.() -> Unit) {
        binding?.block()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                plantInfoViewModel.plantInfo.collect { plantInfoData ->
                    plantInfoData?.mainSpecies?.let { mainSpecies ->
                        updateMainInfo(mainSpecies)
                        updateEdiblePart(mainSpecies.ediblePart)
                    }
                }
            }
        }

        onTabSelected()

        plantInfoViewModel.loadPlantInfo(plantId ?: PLANT_ID_COCONUT)
    }

    private fun onTabSelected(){
        withBinding {
            tabSelectedName = getString(R.string.tab_title_specifications)

            tabLayoutDetail.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    navigationTabs[tabSelectedName]
                        ?.get(tab?.text)
                        ?.let { tabTitle -> fragmentContainerPlantDetails.findNavController().navigate(tabTitle) }
                    tabSelectedName = tab?.text.toString()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) { }

                override fun onTabReselected(tab: TabLayout.Tab?) { }

            })
        }
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