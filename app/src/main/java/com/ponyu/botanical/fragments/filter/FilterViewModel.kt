package com.ponyu.botanical.fragments.filter

import androidx.lifecycle.ViewModel
import com.ponyu.botanical.data.remote.plants.SortType

data class FilterData(
    val sortType: SortType = SortType.NONE,
    val sortBy: Set<String> = setOf()
) : java.io.Serializable

class FilterViewModel(

) : ViewModel(){

    fun applyFilters() {

    }

    fun resetFilters() {

    }
}