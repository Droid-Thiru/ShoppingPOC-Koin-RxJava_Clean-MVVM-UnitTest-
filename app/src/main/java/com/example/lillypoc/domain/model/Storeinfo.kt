package com.example.lillypoc.domain.model


import com.google.gson.annotations.SerializedName

data class Storeinfo(
    @SerializedName("address")
    val address: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("storeID")
    val storeID: String
)