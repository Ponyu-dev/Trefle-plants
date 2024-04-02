package com.ponyu.botanical.data.remote.plants

data class PlantFilterModel(
    val searchQuery: String? = null,
    val options: Map<String, String> = emptyMap()
)

enum class SortType(val sortBy: String) {
    NONE("null"),
    ASCENDING("asc"),
    DESCENDING("desc")
}