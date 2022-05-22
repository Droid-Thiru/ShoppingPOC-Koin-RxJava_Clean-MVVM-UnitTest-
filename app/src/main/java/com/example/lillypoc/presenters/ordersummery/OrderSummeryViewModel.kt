package com.example.lillypoc.presenters.ordersummery

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.lillypoc.base.BaseViewModel
import com.example.lillypoc.base.Constants.SELECTED_PRODUCTS
import com.example.lillypoc.base.Constants.TOTAL_PRICE
import com.example.lillypoc.domain.model.Product
import com.google.gson.Gson

class OrderSummeryViewModel : BaseViewModel() {
    var totalPrice = 0
    var selectedProducts: ArrayList<Product>? = null
    var deliveryAddress = ""

    private val addressValidationMutableLiveData by lazy { MutableLiveData<Pair<String,Boolean>> ()}
    val addressValidationLiveData by lazy { addressValidationMutableLiveData}

    fun onCreate(bundle: Bundle?) {
        bundle?.let {
            /**
             * Get shared data from intent bundle like [totalPrice] and [selectedProducts]
             */
            totalPrice = it.getInt(TOTAL_PRICE, 0)
            selectedProducts = it[SELECTED_PRODUCTS] as ArrayList<Product>
        }
    }

    /**
     * address validation
     */

    fun deliveryAddressValidation(){
        if(deliveryAddress.trim().isEmpty()){
            addressValidationMutableLiveData.value = Pair("Please enter your delivery address",false)
        }else{
            addressValidationMutableLiveData.value = Pair("",true)
        }
    }

    /**
     * create json from [selectedProducts] when click order confirm
     * It will store in the local preference
     */
    fun createJsonFromList(): String? {
        return if (selectedProducts != null) {
            Gson().toJson(selectedProducts)
        } else {
            null
        }
    }
}