package com.ponyu.botanical.ui.footer

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.ponyu.botanical.databinding.ItemPagingFooterBinding
import com.ponyu.botanical.util.ext.executeWithAction

class FooterViewHolder(
    private val binding: ItemPagingFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.executeWithAction {
            footerUiState = FooterUiState(loadState)
        }
    }
}