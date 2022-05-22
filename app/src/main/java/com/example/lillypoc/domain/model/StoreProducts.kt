package com.example.lillypoc.domain.model


import com.google.gson.annotations.SerializedName

data class StoreProducts(
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("storeinfo")
    val storeinfo: Storeinfo
)