package com.ponyu.botanical.fragments.plants

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.ponyu.botanical.R
import com.ponyu.botanical.databinding.FragmentPlantsBinding
import com.ponyu.botanical.fragments.plants.adapter.PlantItemUiState
import com.ponyu.botanical.fragments.plants.adapter.PlantsAdapter
import com.ponyu.botanical.ui.PlantsUiState
import com.ponyu.botanical.ui.footer.FooterAdapter
import com.ponyu.botanical.util.ext.collect
import com.ponyu.botanical.util.ext.collectLast
import com.ponyu.botanical.util.ext.executeWithAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class PlantsFragment : Fragment() {
    private lateinit var binding: FragmentPlantsBinding
    private val listPlantsViewModel: ListPlantsViewModel by viewModels()

    private val plantsAdapter = PlantsAdapter {
        findNavController().navigate(
            R.id.action_plantsFragment_to_plantInfoFragment,
            Bundle().apply { putInt("plantId", it) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_plants,
            container,
            false
        )

        binding.searchViewPlants.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    listPlantsViewModel.search(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    listPlantsViewModel.search(newText)
                    return false
                }
            })

        binding.btnRetry.setOnClickListener { plantsAdapter.retry() }
        setAdapter()
        collectLast(listPlantsViewModel.plantItemsUiStates, ::setUsers)

        return binding.root
    }

    private fun setAdapter() {
        collect(flow = plantsAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setUsersUiState
        )
        binding.recyclerViewPlants.adapter = plantsAdapter.withLoadStateFooter(FooterAdapter(plantsAdapter::retry))
    }

    private fun setUsersUiState(loadState: LoadState) {
        binding.executeWithAction {
            plantsUiState = PlantsUiState(loadState)
        }
    }

    private suspend fun setUsers(userItemsPagingData: PagingData<PlantItemUiState>) {
        plantsAdapter.submitData(userItemsPagingData)
    }
}