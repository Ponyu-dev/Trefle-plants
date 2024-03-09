package com.ponyu.botanical.fragments.plants

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
import com.ponyu.botanical.databinding.FragmentPlantsBinding
import com.ponyu.botanical.fragments.plants.adapter.PlantsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantsFragment : Fragment() {

    private val listPlantsViewModel: ListPlantsViewModel by viewModels()

    private var binding: FragmentPlantsBinding? = null
    private inline fun withBinding(block: FragmentPlantsBinding.() -> Unit) {
        binding?.block()
    }

    private val plantsAdapter = PlantsAdapter {
        Log.d(TAG, "Onclick id = $it")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantsBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withBinding {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    listPlantsViewModel.listPlants.collect {
                        Log.d(TAG, "Plants count = ${it.size}")
                        plantsAdapter.submitList(it)
                    }
                }
            }

            plantsRecyclerView.adapter = plantsAdapter
        }

        listPlantsViewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val TAG = "PlantsFragment"
    }
}