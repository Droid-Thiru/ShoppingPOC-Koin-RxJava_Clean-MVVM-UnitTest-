package com.example.lillypoc.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("availableQty")
    val availableQty: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("productID")
    val productID: String,

    var userQty:Int = 0

): Parcelable{
    fun getUserTotalPrice():Int{
        return price * userQty
    }
}