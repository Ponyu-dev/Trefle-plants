package com.ponyu.botanical.fragments.plants

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.ponyu.botanical.R
import com.ponyu.botanical.databinding.FragmentPlantsBinding
import com.ponyu.botanical.fragments.plants.adapter.PlantsAdapter
import com.ponyu.botanical.ui.PlantItemUiState
import com.ponyu.botanical.ui.PlantsUiState
import com.ponyu.botanical.ui.footer.FooterAdapter
import com.ponyu.botanical.util.ext.collect
import com.ponyu.botanical.util.ext.collectLast
import com.ponyu.botanical.util.ext.executeWithAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class PlantsFragment : Fragment() {
    private lateinit var binding: FragmentPlantsBinding
    private val listPlantsViewModel: ListPlantsViewModel by viewModels()

    @Inject
    lateinit var plantsAdapter: PlantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this.activity as Activity, R.layout.fragment_plants)
        binding.btnRetry.setOnClickListener { plantsAdapter.retry() }
        setAdapter()
        collectLast(listPlantsViewModel.plantItemsUiStates, ::setUsers)
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