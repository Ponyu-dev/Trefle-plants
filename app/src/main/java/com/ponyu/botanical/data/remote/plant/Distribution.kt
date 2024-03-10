package com.ponyu.botanical.data.remote.plant

import com.google.gson.annotations.SerializedName

data class Distribution(
    @SerializedName("native")
    val native: List<String>,
    @SerializedName("introduced")
    val introduced: List<String>,
    @SerializedName("doubtful")
    val doubtful: List<String>,
    @SerializedName("absent")
    val absent: List<String>,
    @SerializedName("extinct")
    val extinct: List<String>
)